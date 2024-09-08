


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