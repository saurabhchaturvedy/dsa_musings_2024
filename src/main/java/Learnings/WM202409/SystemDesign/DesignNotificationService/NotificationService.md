
Problem Statement
=======================
00:00:00	Hi. And welcome to the system design interview channel. Today we design a notification service. Let's start with the problem statement. In the world of web services there are many scenarios when messages need to be sent in a reaction to some event. For example, when credit card transaction amount exceeded a limit and card holder needs to be notified. Or service monitoring system encountered a large number of faults produced by API and on-call engineer needs to be notified. In more general terms, let's say there is a component called Publisher which produces

00:00:35	messages that need to be delivered to a group of other components, called Subscribers. We could have setup a synchronous communication between Publisher and Subscribers, when Publisher calls each Subscriber in some order and waits for the response. But this introduces many different challenges: hard to scale such system when number of subscribers and messages grow and hard to extend such solution to support different types of subscribers. Instead, we can introduce a new system that can register an arbitrary large number of

![img.png](img.png)


![img_1.png](img_1.png)

00:01:07	publishers and subscribers and coordinates message delivery between them. The problem statement is ambiguous, as usual. To make it less vague we need to start asking an interviewer clarifying questions. We need to scope both functional and non-functional requirements. Just on the side note, when we talk about functional requirements, we want to define system behavior, or more specifically APIs - a set of operations the system will support. When we talk about non-functional requirements, we basically mean such system qualities as

00:01:43	scalability, maintainability, testability and others. The core APIs our notification service need to support are: create a topic, publish a message to a topic and subscribe to a topic to receive published messages. Topic represents a named resource to which messages are sent. You can think of it as a bucket that stores messages from a publisher and all subscribers receive a copy of a message from the bucket. As for non-functional requirements, we want our service to be scalable and support a big

00:02:19	number of topics, publishers and subscribers; highly available and survive hardware failures and network partitions; fast, so that messages are delivered to subscribers as soon as possible; and durable, so that messages are not lost and delivered to each subscriber at least once. The interviewer may ask to focus on other requirements as well. For example, security or operational cost. And our usual recommendation is to be proactive and clarify requirements upfront. Let’s move on and outline a high-level architecture.

![img_2.png](img_2.png)


High Level Architecture
===============================
00:02:56	All requests coming from our clients will go through a load balancer first. This will ensure requests are equally distributed among requests processing servers. And the component that does this initial request processing is a FrontEnd service. We will use a database to store information about topics and subscriptions. We will hide the database behind another miscroservice, Metadata service. There are several reasons for this decision. First, separation of concerns, a design principle that teaches us to provide access to the database

00:03:33	through a well-defined interface. It greatly simplifies maintenance and ability to make changes in the future. Second, Metadata service will act as a caching layer between the database and other components. We do not want to hit database with every message published to the system. We want to retrieve topic metadata from cache. Next, we need to store messages for some period of time. This period will generally be short if all subscribers are available and message was successfully sent to all of them.

00:04:07	Or we may need to store messages a bit longer (say several days), so that messages can be retried later if some subscriber is not available right now. And one more component we need is the one that retrieves messages from the message store and sends them to subscribers. Sender also needs to call Metadata service to retrieve information about subscribers. When create topic and subscribe APIs are called, we just need to store all this information in the database. It was not hard to define this architecture, right?

00:04:40	We followed some pretty common patterns, like having a FrontEnd service behind a load balancer. And having a database for storing metadata and hide this database behind some facade, which is a distributed cache microservice. And I believe it is evident that we need components for storing messages and sending them. By the way, the pattern that consists of load balancer, frontend, metadata store and service is so common in the world of distributed systems, that you can apply it during many system design

![img_3.png](img_3.png)

Frontend Service
====================

![img_4.png](img_4.png)


00:05:13	interview discussions. So, please remember it. Next, let’s take a look at each component in more details and start with FrontEnd service. FrontEnd is a lightweight web service responsible for: request validation, authentication and authorization, SSL termination, server-side encryption, caching, throttling, request dispatching and deduplucation, usage data collection. We briefly touched all concepts from this list in our previous video about distributed message queue and we highly encourage you to check out that video.

00:05:53	Instead of repeating that information, let me share with you several other FrontEnd service details. Specifically, let’s take a look at a FrontEnd host and discuss what components live there. When request lands on the host, the first component that picks it up is a Reverse Proxy. Reverse proxy is a lightweight server responsible for several things. Such as SSL termination, when requests that come over HTTPS are decrypted and passed further in unencrypted form. At the same time proxy is responsible for encrypting responses while sending them back

Frontend Service Host
==========================

![img_5.png](img_5.png)

00:06:31	to clients. Second responsibility is compression (for example with gzip), when proxy compresses responses before returning them back to clients. This way we reduce the amount of bandwidth required for data transfer. This feature is not relevant for notification service, as in our case responses are tiny and mostly represent acknowledgment that request have completed successfully. But this feature may be very useful for systems when large amount of data is returned by the service. Next proxy feature we may use is to properly handle FrontEnd service slowness.

00:07:09	We may return HTTP 503 status code (which is service unavailable) if FrontEnd service becomes slow or completely unavailable. Reverse proxy then passes request to the FrontEnd web service. We know that for every message that is published, FrontEnd service needs to call Metadata service to get information about the message topic. To minimize number of calls to Metadata service, FrontEnd may use local cache. We may use some well-known cache implementations (like Google Guava) or create our own implementation

00:07:43	of LRU cache (least recently used). FrontEnd service also writes a bunch of different logs. What information is this? We definitely need to log information about service health, write down exceptions that happen in the service. We also need to emit metrics. This is just a key-value data that may be later aggregated and used to monitor service health and gather statistics. For example, number of requests, faults, calls latency, we will need all this information for system monitoring. Also, we may need to write information that may be used for audit, for example log who

00:08:25	and when made requests to a specific API in the system. Important to understand here, is that FrontEnd service is responsible for writing log data. But the actual log data processing is managed by other components, usually called agents. Agents are responsible for data aggregation and transferring logs to other system, for post processing and storage. This separation of responsibilities is what helps to make FrontEnd service simpler, faster and more robust. The next component in the notification system is a Metadata service.

Metadata Service
=========================


00:09:01	A web service responsible for storing information about topics and subscriptions in the database. It is a distributed cache. When our notification service becomes so popular that we have millions of topics, all this information cannot be loaded into a memory on a single host. Instead, information about topics is divided between hosts in a cluster. Cluster represents a consistent hashing ring. Each FrontEnd host calculates a hash, for example MD5 hash, using some key, for example a combination of topic name and topic owner identifier.

00:09:40	Based on the hash value, FrontEnd host picks a corresponding Metadata service host. Let’s discuss in more details two different approaches how FrontEnd hosts know which Metadata service host to call. In the first option we introduce a component responsible for coordination. This component knows about all the Metadata service hosts, as those hosts constantly send heartbeats to it. Each FrontEnd host asks Configuration service what Metadata service host contains data for a specified hash value. Every time we scale out and add more Metadata service hosts, Configuration service becomes

00:10:24	aware of the changes and re-maps hash key ranges. In the second option we do not use any coordinator. Instead, we make sure that every FrontEnd host can obtain information about all Metadata service hosts. And every FrontEnd host is notified when more Metadata service hosts are added or if any Metadata host died due to a hardware failure. There are different mechanisms that can help FrontEnd hosts discover Metadata service hosts. We will not dive into this topic here, only mention the Gossip protocol.

![img_6.png](img_6.png)


00:11:00	This protocol is based on the way that epidemics spread. Computer systems typically implement this type of protocol with a form of random "peer selection": with a given frequency, each machine picks another machine at random and shares data. After being initially processed by FrontEnd service, message is then passed to the Temporary Storage service. Why do we call it temporary and not just storage? Because messages supposed to stay in this storage for a very short period of time. Sooner we can deliver messages to subscribers is better, unless topic is configured to deliver

![img_7.png](img_7.png)
00:11:36	messages with some delay. What do we expect from the Temporary Storage service? First, it must be fast, highly-available and scalable. Second, it has to guarantee data persistence, so that messages survive unavailability of a subscriber. And can be re-delivered later. Several design options can be considered and this is a great opportunity for you to show breadth and depth of your knowledge to the interviewer. Let’s see how many different directions the interview may go from here. You can start discussing databases with the interviewer, consider pros and cons of SQL

00:12:13	versus NoSQL databases, evaluate different NoSQL database types and give specific names to the interviewer. For example, when we consider SQL or NoSQL for storing messages, we may mention that we do not need ACID transactions, we do not need to run complex dynamic queries, we do not plan to use this storage for analytics or data warehousing. Instead, we need a database that can be easily scaled for both writes and reads. It should be highly available and tolerate network partitions. Summing all these up, it is clear that NoSQL wins for our use case.

00:12:53	If we need to choose a particular NoSQL database type, we need to mention that messages have limited size (let’s say not more than 1 MB), meaning that we do not actually need a document store. And there is no any specific relationship between messages. And thus, we can exclude graph type as well. Which leaves us with either column or key-value database types. And we can mention several well-regarded names of these two database types. For example, Apache Cassandra and Amazon DynamoDB. Next option we can evaluate is in-memory storage.

00:13:31	We better choose an in-memory store that supports persistence, so that messages can live for several days before being dropped. And also mention some great in-memory storage solutions like Redis. One more option to consider - message queues. Distributed message queues have all the characteristics we require and also can be discussed in more details. To be prepared for this conversation, please check out our video on how to design a distributed message queue. And if you want to further impress interviewer, you can talk about other options, for example

00:14:07	stream-processing platforms. Discuss pros and cons and compare this option with a distributed queue solution. And of course, do not forget to mention some best-in-class solutions, for example Apache Kafka and Amazon Kinesis. Here we reference AWS solutions, but feel free to mention similar solutions from other public clouds. Whatever solution you feel comfortable with. Out of these 4 options, which one do you consider the best? Please let me know in the comments. I have my lucky winner, but I am really curious what you think.

00:14:41	After message is successfully published and stored in the Temporary Storage, we now can start sending this message to subscribers. Let’s take a look at the Sender component. You will see that ideas on which Sender service is built upon, can easily be applied to other distributed systems and not just the Notification service. If you design a solution that involves data retrieval, processing and sending results in a fan-out manner, meaning that messages are sent to multiple destinations in parallel,

00:15:14	think of the ideas we will discuss next. The first thing that Sender does is message retrieval. This is achieved by having a pool of threads, where each thread tries to read data from the Temporary Storage. We can implement a naive approach and always start a predefined number of message retrieval threads. The problem with this approach is that some threads may be idle, as there may not be enough messages to retrieve. Or another extreme, when all threads may quickly become occupied and the only way to scale

00:15:48	message retrieval would be adding more Sender hosts. A better approach is to keep track of idle threads and adjust number of message retrieval threads dynamically. If we have too many idle threads, no new threads should be created. If all threads are busy, more threads in the pool can start reading messages. This not only helps to better scale the Sender service, it also protects Temporary Storage service from being constantly bombarded by Sender service. This is especially useful when Temporary Storage service experiences performance degradation,

00:16:24	and Sender service can lower the rate of message reads to help Temporary Storage service to recover faster. How can we implement this auto-scaling solution? Semaphores to the rescue. Conceptually, a semaphore maintains a set of permits. Before retrieving the next message, thread must acquire a permit from the semaphore. When the thread has finished reading the message a permit is returned to the semaphore, allowing another thread from the pool to start reading messages. What we can do is to adjust a number of these permits dynamically, based on the existing

00:17:00	and desired message read rate. After message is retrieved, we need to call Metadata service to obtain information about subscribers. Probably, some of you are wondering why we need to call Metadata service here, if we already called it in FrontEnd service and could have passed information about subscribers along with the message. Good point to discuss with the interviewer. One of the main reasons not to pass this information along with the message itself, is that list of subscribers may be relatively big.

00:17:32	For example, several thousands of HTTP endpoints, or a long list of email addresses. We will need to store all this information with every incoming message and our Temporary Storage service will need to pay this price. Not all key-value and column storages can store big messages, which may require us to use document database instead. These all are very good consideration to mention to the interviewer. After we obtained a list of subscribers, we can start sending messages to all of them. Can you think of a good way to do this?

00:18:06	Should we just iterate over a list of subscribers and make a remote call to each one of them? What should we do if message delivery failed to one subscriber from the list? What if one subscriber is slow and delivery to other subscribers is impacted due to this? If you think about all these questions or if interviewer makes you thinking about them, you will probably figure out that this option is far from optimal. The better option is to split message delivery into tasks. Where each task is responsible for delivery to a single subscriber.

00:18:40	This way we can deliver all messages in parallel and isolate any bad subscriber. So, let's introduce two new components - Task Creator and Executor. These components are responsible for creating and scheduling a single message delivery task. How can we implement these components? Create a pool of threads, where each thread is responsible for executing a task. In Java for example, we can use a ThreadPoolExecutor class. And similar to the Message Retriever component, we can also use semaphores to keep track of

00:19:16	available threads in the pool. If we have enough threads to process newly created tasks, we simply submit all tasks for processing. If we do not have enough treads available at the moment, we may postpone or stop message processing and return the message back to the Temporary Storage. In this case, a different Sender service host may pick up this message. And that host may have enough threads to process the message. This approach allows to better handle slow Sender service host issues. Each task is responsible for message delivery to a single subscriber.

00:19:53	Tasks may delegate actual delivery to other microservices. For example, a microservice responsible for sending emails or SMS messages. So far we have discussed all the key components of the notification service. Let’s see what other topics may be brought up during the interview. How to make sure notifications will not be sent to users as spam? We need to register subscribers. All subscribers need to confirm they agree to get notification from our service. Every time new subscriber is registered, we may send a confirmation message to the HTTP

00:20:28	endpoint or email. Endpoint and email owners need to confirm the subscription request. When publishers send messages to our notification service, FrontEnd service will make sure duplicate submissions are eliminated. This helps to avoid duplicates while accepting messages from publishers. But when the Sender service delivers messages to subscribers, retries caused by network issues or subscriber’s internal issues may cause duplicate messages at the subscriber end. So, subscribers also become responsible for avoiding duplicates.

00:21:05	If retries cause duplicates, maybe we do not need to retry? Good question. Retry is one of the options to guarantee at least once message delivery. Our system may retry for hours or even days until messages is successfully delivered or maximum allowed number of retries have been reached. Other option may be to send undelivered messages to a different subscriber. Or store such messages in a system that can be monitored by subscribers and subscribers then decide what to do with undelivered messages.

00:21:41	It would be great if our notification system provides a way for subscribers to define retry policy and what to do in cases when a message cannot be delivered after retries limit is reached. Does our system guarantee any specific message order, for example first-come-first-delivered? What do you think? Please stop this video and look at the architecture one more time. And the short answer is no, it does not guarantee any specific order. Even if messages are published with some attribute that preserves the order, for example sequence

00:22:15	number or timestamps, delivery of messages does not honor this. Delivery tasks can be executed in any order, slower Sender hosts may fall behind, message delivery attempt may fail and retry will arrive in a wrong order. Security always has to be a top priority. We need to make sure only authenticated publishers can publish messages, only registered subscribers can receive them, messages are always delivered to the specified set of subscribers and never to anyone else. Encryption using SSL over HTTP helps to protect messages in transit.

00:22:53	And we also need to encrypt messages while storing them. We need to setup monitoring for every microservice we discussed, as well as for the end-to-end customer experience. We also need to give customers ability to track state of their topics. For example, number of messages waiting for delivery, number of messages failed to deliver, etc. This usually means that integration with a monitoring system is required. We will have more videos explaining this topic. Let’s take one final look at our high-level architecture and evaluate whether all non-functional

00:23:28	requirements are met. Did we design a scalable system? Yes. Every component is horizontally scalable. Sender service also has a great potential for vertical scaling, when more powerful hosts can execute more delivery tasks. Did we design a highly available system? Yes. There is no single point of failure, each component is deployed across several data centers. Did we design a highly performant system? To answer this question let’s take a more thorough look. FrontEnd service is fast. We made it responsible for several relatively small and cheap activities.

00:24:06	We delegated several other activities to agents that run asynchronously and does not impact request processing. Metadata service is a distributed cache. It is fast as we store data for active topics in memory. We discussed several Temporary Storage design options and mentioned 3-rd party solutions that are fast. And our Sender service splits message delivery into granular tasks, so that each one of them can be optimally performed. Did we design a durable system? Yes. Whatever Temporary Storage solution we choose, data will be stored in the redundant manner,

00:24:47	when several copies of a message is stored across several machines, and ideally across several data centers. We also retry messages for a period of time to make sure they are delivered to every subscriber at least once. And that is it for today’s system design interview question. Thank you for watching this video. If you have any questions please leave them in the comments below. And I will see you next time.
