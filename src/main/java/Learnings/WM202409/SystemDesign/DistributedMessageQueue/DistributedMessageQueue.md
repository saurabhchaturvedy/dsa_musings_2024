


Distributed Message Queue
================================

* Hi. And welcome to the system design interview channel. Today we design a distributed message queue. First, let’s make sure we are on the same page regarding the problem statement. What is a distributed message queue? Let's say there are two web-services called producer and consumer, and they need to communicate with each other. One option is to setup a synchronous communication, when producer makes a call to a consumer and waits for a response. This approach has its own pros and cons. Synchronous communication is easier and faster to implement.

* At the same time synchronous communication makes it harder to deal with consumer service failures. We need to think when and how to properly retry failed requests, how not to overwhelm consumer service with too many requests and how to deal with a slow consumer service host. Another option is to introduce a new component that helps to setup asynchronous communication. Producer sends data to that component and exactly one consumer gets this data a short time after. Such component is called a queue.

* And it is distributed, because data is stored across several machines. Please do not confuse queue with a topic. In case of a topic, message that is published goes to each and every subscriber. In case of a queue, message is received by one and only one consumer. And as it often happens with interview questions, the statement is ambiguous. What are the functional requirements? What non-functional requirements have a priority over others? What is a scale we need to deal with? All these questions need to be clarified with the interviewer.


![img.png](img.png)


Functional Requirements
==================================

* Let's do our best and define requirements ourselves. Starting with functional requirements. At this stage of the interview it may be hard to come up with a definitive set of requirements. And it’s usually not needed. Time limit allows us to only focus on several core APIs, like send message and receive message. As for non-functional requirements, we want our system to be scalable and handle load increase, highly available and tolerate hardware and network failures, highly performant, so that both send and receive operations are fast, and durable, so that data is persisted
* once submitted to the queue. And of course, there may be many other requirements either explicitly stated by the interviewer or intentionally omitted. Among functional requirements, we can be asked to support create and delete queue APIs, or delete message API. There may be specific requirements for the producer (for example system needs to avoid duplicate submissions), or security requirements, or an ask to implement a specific ordering guarantee. As for non-functional requirements, the interviewer may define specific service level agreement
* numbers (so called SLA, for example minimum throughput our system needs to support), or requirements around cost-effectiveness (for example system needs to minimize hardware cost or operational support cost). But do not worry if you can’t think of all the possible requirements. Interviewer is your friend and will help to scope the problem. You just need to be proactive and outline main use cases. Now let’s start drafting the architecture. Let’s start with components that are common for many distributed systems.

![img_1.png](img_1.png)

* First, we need a virtual IP. VIP refers to the symbolic hostname (for example myWebService.domain.com) that resolves to a load balancer system. So next, we have a load balancer. A load balancer is a device that routs client requests across a number of servers. Next, we have a FrontEnd web service. A component responsible for initial request processing, like validation, authentication, etc. Queue metadata information like its name, creation date and time, owner and any other configuration settings will be stored in a database.
* And best practices dictate that databases should be hidden behind some facade, a dedicated web service responsible for handling calls to a database. And we need a place to store queue messages. So, lets introduce a backend web service, that will be responsible for message persistence and processing. Now, let’s take a look at each component one by one. Load balancing is a big topic. And unless interviewer encourages you to dive deep into load balancing topic, we better not deviate too much from the main question of the interview.

* Always try to stay focused on what really matters. Internals of how load balancers work may not matter, but in order to make sure non-functional requirements to the system we build are fully met, we need to explain how load balancers will help us achieve high throughput and availability. When domain name is hit, request is transferred to one of the VIPs registered in DNS for our domain name. VIP is resolved to a load balancer device, which has a knowledge of FrontEnd hosts. By looking at this architecture, several questions have probably popped in your head?

High Level Architecture
================================

![img_2.png](img_2.png)

VIP and Load Balancer
====================================


* First, load balancer seems like a single point of failure. What happens if load balancer device goes down? Second, load balancers have limits with regards to number of requests they can process and number of bytes they can transfer. What happens when our distributed message queue service becomes so popular that load balancer limits are reached? To address high availability concerns, load balancers utilize a concept of primary and secondary nodes. The primary node accepts connections and serves requests while the secondary node monitors
* the primary. If, for any reason, the primary node is unable to accept connections, the secondary node takes over. As for scalability concerns, a concept of multiple VIPs (sometimes referred as VIP partitioning) can be utilized. In DNS we assign multiple A records to the same DNS name for the service. As a result, requests are partitioned across several load balancers. And by spreading load balancers across several data centers, we improve both availability and performance. Let's move on to the next component, which is a FrontEnd web service.
* FrontEnd is a lightweight web service, consisting of stateless machines located across several data centers. FrontEnd service is responsible for: request validation, authentication and authorization, SSL termination, server-side data encryption, caching, rate limiting (also known as throttling), request dispatching, request deduplication, usage data collection. Let’s discuss some basics of these features. Request validation helps to ensure that all the required parameters are present in the request and values of these parameters honor constraints.

![img_3.png](img_3.png)

Frontend Web Service
==========================

* FrontEnd is a lightweight web service, consisting of stateless machines located across several data centers. FrontEnd service is responsible for: request validation, authentication and authorization, SSL termination, server-side data encryption, caching, rate limiting (also known as throttling), request dispatching, request deduplication, usage data collection. Let’s discuss some basics of these features. Request validation helps to ensure that all the required parameters are present in the request and values of these parameters honor constraints.

![img_4.png](img_4.png)

![img_5.png](img_5.png)

![img_6.png](img_6.png)

![img_7.png](img_7.png)

![img_8.png](img_8.png)

![img_9.png](img_9.png)

![img_10.png](img_10.png)

![img_11.png](img_11.png)

![img_12.png](img_12.png)

![img_13.png](img_13.png)



Metadata Service
==========================


* And even though FrontEnd service has many responsibilities, the rule of thumb is to keep it as simple as possible. Moving on to the next component, which is Metadata service. Metadata service stores information about queues. Every time queue is created, we store information about it in the database. Conceptually, Metadata service is a caching layer between the FrontEnd and a persistent storage. It handles many reads and a relatively small number of writes. As we read every time message arrives and write only when new queue is created.

* Even though strongly consistent storage is preferred to avoid potential concurrent updates, it is not strictly required. Lets take a look at different approaches of organizing cache clusters. The first option is when cache is relatively small and we can store the whole data set on every cluster node. FrontEnd host calls a randomly chosen Metadata service host, because all the cache cluster nodes contain the same information. Second approach is to partition data into small chunks, called shards.

* Because data set is too big and cannot be placed into a memory of a single host. So, we store each such chunk of data on a separate node in a cluster. FrontEnd then knows which shard stores the data and calls the shard directly. And the third option is similar to the second one. We also partition data into shards, but FrontEnd does not know on what shard data is stored. So, FrontEnd calls a random Metadata service host and host itself knows where to forward the request to. In option one, we can introduce a load balancer between FrontEnd and Metadata service.

* As all Metadata service hosts are equal and FrontEnd does not care which Metadata host handles the request. In option two and three, Metadata hosts represent a consistent hashing ring. Do not worry if this term is completely new to you. Distributed cache topic is big and we will have a separate video on how to design a distributed cache. Components we built so far were relatively straightforward. Not easy of course, but if you have understanding of several core design principles, you will at least progress thus far in the interview.

![img_14.png](img_14.png)


Backend Service
=======================


* By the way, the set of components we just discussed: VIP + Load Balancer + FrontEnd web service + Metadata web service that represents a caching layer on top of a database is so popular in the world of distributed systems, that you may consider it a standard and apply to many system designs. Now, let’s take a look at the backend component. This is where the real challenge starts. To understand how backend service architecture may look like, let’s try to answer some important questions first. By the way, if you stuck during the interview, not knowing how to progress further, start

* asking yourself questions. Asking right questions helps to split the problem into more manageable pieces. Plus, it helps to establish a better communication channel with the interviewer. Interviewer will let you know whether you are on the right path or not. So, what those question may be? We need to figure out where and how messages are stored, right? Is database an option? Yes, it is. But not the best one and let me explain why. We are building a distributed message queue, a system that should be able to handle a very

* high throughput. And this means that all this throughput will be offloaded to the database. In other words, a problem of building a distributed message queue becomes a problem of building a database that can handle high throughput. And we know that highly-available and scalable databases exist out there. And if you are a junior software engineer, it is totally reasonable to say that we will utilize a 3-rd party database solution and stop right there. But for a senior position, we need to either explain how to build a distributed database

* (and we promise you a separate video on this) or we need to keep seeking for other options. And if not a database, where else can we store data? Who thought about memory? Please let me know in the comments. And you are correct by the way. As well as those who said file system. As we may need to store messages for days or even weeks, we need a more durable storage, like a local disk. At the same time newly arrived messages may live in memory for a short period of time or until memory on the backend host is fully utilized.

* Next question we should ask ourselves: how do we replicate data? And I believe you may already figured this out. We will send copies of messages to some other hosts, so that data can survive host hardware or software failures. And finally, let's think about how FrontEnd hosts select backend hosts for both storing messages and retrieving them. We can leverage Metadata service, right? So, let's summarize what we have just discussed. Message comes to the FrontEnd, FrontEnd consults Metadata service what backend host to send

* data to. Message is sent to a selected backend host and data is replicated. And when receive message call comes, FrontEnd talks to Metadata service to identify a backend host that stores the data. Now, let's dive deep into the backend service architecture. We will consider two options of how backend hosts relate to each other. In the first option, each backend instance is considered a leader for a particular set of queues. And by leader we mean that all requests for a particular queue (like send message and


![img_15.png](img_15.png)


Backend Service Detailed Architecture
======================================

* receive message requests) go to this leader instance. Let's look at the example. Send message request comes to a FrontEnd instance. Message comes to a queue with ID equal to q1. FrontEnd service calls Metadata service to identify a leader backend instance for this queue. In this particular example, instance B is a leader for q1. Message is sent to the leader and the leader is fully responsible for data replication. When receive message request comes to a FrontEnd instance, it also makes a request to the Metadata

* service to identify the leader for the queue. Message is then retrieved from the leader instance and leader is responsible for cleaning up the original message and all the replicas. We need a component that will help us with leader election and management. Let’s call it In-cluster manager. And as already mentioned, in-cluster manager is responsible for maintaining a mapping between queues, leaders and followers. In-cluster manager is a very sophisticated component. It has to be reliable, scalable and performant.

* Creating such a component from scratch is not an easy task. Let’s see if we can avoid leader election in the first place. Can you think of an option when all instances are equal? Please pause this video and think for a while. In the second option, we have a set of small clusters, each cluster consists of 3-4 machines distributed across several data centers. When send message request comes, similar to the previous design option, we also need to call Metadata service to identify which cluster is responsible for storing messages for the

![img_16.png](img_16.png)

Small Cluster of Independent Hosts
=====================================

* q1 queue. After that we just make a call to a randomly selected instance in the cluster. And instance is responsible for data replication across all nodes in the cluster. When receive message request comes and we identified which cluster stores messages for the q1 queue, we once again call a randomly selected host and retrieve the message. Selected host is responsible for the message cleanup. As you may see, we no longer need a component for leader election, but we still need something that will help us to manage queue to cluster assignments.

* Let’s call this component an Out-cluster manager (not the best name, I know, but naming is hard). And this component will be responsible for maintaining a mapping between queues and clusters. Is out-cluster manager a simpler component than in-cluster manager? It turns out that not really. While in-cluster manager manages queue assignment within the cluster, out-cluster manager manages queue assignment across clusters. In-cluster manager needs to know about each and every instance in the cluster.

![img_17.png](img_17.png)

![img_18.png](img_18.png)


What Else is Important
======================================


* Let’s see what else is important to mention while discussing distributed message queues. Queue creation and deletion. Queue can be auto-created, for example when the first message for the queue hits FrontEnd service, or we can define API for queue creation. API is a better option, as we will have more control over queue configuration parameters. Delete queue operation is a bit controversial, as it may cause a lot of harm and must be executed with caution. For this reason, you may find examples of well-known distributed queues that do not

* expose deleteQueue API via public REST endpoint. Instead, this operation may be exposed through a command line utility, so that only experienced admin users may call it. As for a message deletion, there are several options at our disposal. One option is not to delete a message right after it was consumed. In this case consumers have to be responsible for what they already consumed. And it is not as easy as it sounds. As we need to maintain some kind of an order for messages in the queue and keep track of

* the offset, which is the position of a message within a queue. Messages can then be deleted several days later, by a job. This idea is used by Apache Kafka. The second option, is to do something similar to what Amazon SQS is doing. Messages are also not deleted immediately, but marked as invisible, so that other consumers may not get already retrieved message. Consumer that retrieved the message, needs to then call delete message API to delete the message from a backend host. And if the message was not explicitly deleted by a consumer, message becomes visible and

* may be delivered and processed twice. We know that messages need to be replicated to achieve high durability. Otherwise, if we only have one copy of data, it may be lost due to unexpected hardware failure. Messages can be replicated synchronously or asynchronously. Synchronously means that when backend host receives new message, it waits until data is replicated to other hosts. And only if replication is fully completed, successful response is returned to a producer. Asynchronous replication means that response is returned back to a producer as soon as

* message is stored on a single backend host. Message is later replicated to other hosts. Both options have pros and cons. Synchronous replication provides higher durability, but with a cost of higher latency for send message operation. Asynchronous replication is more performant, but does not guarantee that message will survive backend host failure. There are three main message delivery guarantees. At most once, when messages may be lost but are never redelivered. At least once, when messages are never lost but may be redelivered.

* And exactly once, when each message is delivered once and only once. And you probably have a question already, why do we need three? Will anyone ever want other than exactly once delivery? Great question, and the simple answer is that it is hard to achieve exactly once delivery in practice. In a distributed message queue system there are many potential points of failure. Producer may fail to deliver or deliver multiple times, data replication may fail, consumers may fail to retrieve or process the message.

* All this adds complexity and leads to the fact that most distributed queue solutions today support at-least-once delivery, as it provides a good balance between durability, availability and performance. With a pull model, consumer constantly sends retrieve message requests and when new message is available in the queue, it is sent back to a consumer. With a push model, consumer is not constantly bombarding FrontEnd service with receive calls. Instead, consumer is notified as soon as new message arrives to the queue.

* And as always, there are pros and cons. Here I will not enumerate all of them, will simply state that from a distributed message queue perspective pull is easier to implement than a push. But from a consumer perspective, we need to do more work if we pull. Many of us think of FIFO acronym when we hear about queues. FIFO stands for first-in, first-out, meaning that the oldest message in a queue is always processed first. But in distributed systems, it is hard to maintain a strict order. Message A may be produced prior to message B, but it is hard to guarantee that message

* A will be stored and consumed prior to message B. For these reasons many distributed queue solutions out there either does not guarantee a strict order. Or have limitations around throughput, as queue cannot be fast while it’s doing many additional validations and coordination to guarantee a strict order. With regards to security, we need to make sure that messages are securely transferred to and from a queue. Encryption using SSL over HTTPS helps to protect messages in transit. And we also may encrypt messages while storing them on backend hosts.

* We discussed this when talked about FrontEnd service responsibilities. Monitoring is critical for every system. With regards to distributed message queue, we need to monitor components (or microservices) that we built: fronted, metadata and backend services. As well as provide visibility into customer’s experience. In other words, we need to monitor health of our distributed queue system and give customers ability to track state of their queues. Each service we built has to emit metrics and write log data.

* As operators of these services we need to create dashboards for each microservice and setup alerts. And customers of our queue have to be able to create dashboards and set up alerts as well. For this purpose, integration with monitoring system is required. Do not forget to mention monitoring aspect to the interviewer. Many times this topic is omitted by candidates, but it is very important. Let's take one final look at the architecture we built. And evaluate whether non-functional requirements are fulfilled.

* Is our system scalable? Yes. As every component is scalable. When load increases, we just add more load balancers, more FrontEnd hosts, more Metadata service cache shards, more backend clusters and hosts. Is our system highly available? Yes. As there is no a single point of failure, each component is deployed across several data centers. Individual hosts may die, network partitions may happen, but with this redundancy in place our system will continue to operate. Is our system highly performant?

* It’s actually very well depends on the implementation, hardware and network setup. Each individual microservice needs to be fast. And we need to run our software in high-performance data centers. Is our system durable? Sure. We replicate data while storing and ensure messages are not lost during the transfer from a producer and to a consumer. And that is it for today’s system design interview question. Thank you for watching this video. If you have any questions please leave them in the comments below.

![img_19.png](img_19.png)

![img_20.png](img_20.png)
