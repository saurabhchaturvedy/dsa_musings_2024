

Design Rate Limiter
==============================

* Hi. And welcome to the system design interview channel. Today we design a rate limiting solution, a popular interview question. This may not be obvious, but almost all well-known public services use rate limiting for their APIs: Youtube, Facebook, Twitter, Instagram, cloud services, you name it. And as usual, let’s start with the problem statement. Let’s imaging we launched a web application. And the application became highly popular. Meaning that thousands of clients send thousands of requests every second to the front-end

Problem Statement
================================

* web service of our application. Everything works well. Until suddenly one or several clients started to send much more requests than they did previously. And this may happen due to a various of reasons. For example, our client is another popular web service and it experienced a sudden traffic spike. Or developers of that web service started to run a load test. Or this is just a malicious client who tried to DDoS our service. All these situations may lead to a so called “noisy neighbor problem”, when one client

* utilizes too much shared resources on a service host, like CPU, memory, disk or network I/O. And because of this, other clients of our application start to experience higher latency for their requests, or higher rate of failed requests. One of the ways to solve a “noisy neighbor problem” is to introduce a rate limiting (also known as throttling). Throttling helps to limit the number of requests a client can submit in a given amount of time. Requests submitted over the limit are either immediately rejected or their processing is

* delayed. If this is the first time you hear about rate limiting concept, you might already have several questions to me. Let’s pretend I am your interviewer. And here are some of the things you might want to clarify with me. Ok, the first question I hear from you is that this problem does not have a lot of sense. It should be solved by scaling out the cluster of hosts that run our web service. And ideally, by some kind of auto-scaling, right? Glad you asked. And the problem with scaling up or scaling out is that it is not happening immediately.

![img.png](img.png)


Requirements Clarification
=================================


![img_1.png](img_1.png)

* delayed. If this is the first time you hear about rate limiting concept, you might already have several questions to me. Let’s pretend I am your interviewer. And here are some of the things you might want to clarify with me. Ok, the first question I hear from you is that this problem does not have a lot of sense. It should be solved by scaling out the cluster of hosts that run our web service. And ideally, by some kind of auto-scaling, right? Glad you asked. And the problem with scaling up or scaling out is that it is not happening immediately.

* Even autoscaling takes time. And by the time scaling process completes it may already be late. Our service may already crash. Looks like I still have not convinced you. And the next question I hear from you is about other means of how rate limiting can be achieved. Specifically, you mention load balancers and their ability to limit a number of simultaneous requests that load balancer sends to each application server. Load balancers indeed may prevent too many requests to be forwarded to an application

* server. Load balancer will either reject any request over the limit or send the request to a queue, so that it can be processed later. But the problem with this mechanism - it is indiscriminate. What do I mean by this? Let’s say our web service exposes several different operations. Some of them are fast operations, they take little time to complete. But some operations are slow and heavy and each request may take a lot of processing power. Load balancer does not have knowledge about a cost of each operation.

* And if we want to limit number of requests for a particular operation, we can do this on application server only, not at a load balancer level. Ok, looks like I was able to convince you that “noisy neighbor problem” is a real problem and throttling helps to make web services more resilient. And I think you already have some ideas how to solve it. But one question is still sitting in your head. The problem does not seem to be a system design problem. Algorithmic problem? Yes, as we need to define data structures and algorithm to count how many requests client

* has made so far. Object-oriented design problem? Probably, as we may need to design a set of classes to manage throttling rules. Rules define an allowed throttling limit for each operation. So, if we implement throttling for a single host, are we done? In an ideal world - yes. But not in the real world. Your thought process is very reasonable. If we have a load balancer in front of our web service and this load balancer spreads requests evenly across application servers and each request takes the same amount of

* All this leads to a conclusion that we will need a solution where application servers will communicate with each other and share information about how many client requests each one of them processed so far. If after this conversation between me and myself on your behalf you do not consider me crazy, let's move on and formalize requirements. Both functional and non-functional. Functional requirements are simple. For a given request our rate limiting solution should return a boolean value, whether request

* is throttled or not. As for non-functional requirements we need rate limiter to be fast (as it will be called on every request to the service), accurate (as we do not want to throttle customers unless it is absolutely required), and scalable (so that rate limiter scales out together with the service itself). If we need to add more hosts to the web service cluster, this should not be a problem for the rate limiter. What other requirements you can think of? What else the interviewer may be interested in?

* is throttled or not. As for non-functional requirements we need rate limiter to be fast (as it will be called on every request to the service), accurate (as we do not want to throttle customers unless it is absolutely required), and scalable (so that rate limiter scales out together with the service itself). If we need to add more hosts to the web service cluster, this should not be a problem for the rate limiter. What other requirements you can think of? What else the interviewer may be interested in?


* What about high availability and fault tolerance? Two common requirements for many distributed systems. Are they important for a rate limiting solution? Mmm...not so much. If rate limiter cannot make a decision quickly due to any failures, the decision is always not to throttle. And this makes a lot of sense, right? If we do not know whether throttle or not - we do not throttle. Because we may need to introduce rate limiting in many services, the interviewer may ask us to think about ease of integration.

![img_2.png](img_2.png)


* So that every service team in the organization can integrate with our rate limiting solution as seamlessly as possible. This is a good requirement. And we will talk more about it later in this video. Ok, so we are done with the requirements. And ready to start building a solution. And here I want to start with the recommendation that you’ve heard millions of times already. Start with a simple solution first. This recommendation is everywhere out there: books, blogs, training videos. And it is amazing how many candidates ignore it, unfortunately.

* Whether this is an algorithmic problem or system design problem, it always makes sense to start with (or at least mention to the interviewer) a simple solution and evolve the solution along the interview. With this in mind, let’s implement a rate limiting solution for a single server first. So, no communication between servers just yet. The first citizen of the rate limiting solution on the service host is the rules retriever. Each rule specifies a number of requests allowed for a particular client per second.


Request Processing
========================

* These rules are defined by service owners and stored in a database. And there is a web service that manages all the operation with rules. Rules retriever is a background process that polls Rules service periodically to check if there are any new or modified rules. Rules retriever stores rules in memory on the host. When request comes, the first thing we need to do is to build a client identifier. Let’s call it a key, for short. This may be a login for registered clients or remote IP address or some combination of


![img_3.png](img_3.png)


* attributes that uniquely identify the client. The key is then passed to the Rate Limiter component, that is responsible for making a decision. Rate Limiter checks the key against rules in the cache. And if match is found, Rate Limiter checks if number of requests made by the client for the last second is below a limit specified in the rule. If threshold is not exceeded, request is passed further for processing. If threshold is exceeded, the request is rejected. And there are three possible options in this case.
* Our service may return a specific response status code, for example service unavailable or too many requests. Or we can queue this request and process it later. Or we can simply drop this request on the floor. Nothing scary so far, right? The thinking process has been pretty straightforward. We know we need a database to store the rules. And we need a service on top of this database for all the so-called CRUD operations (create, read, update, delete). We know we need a process to retrieve rules periodically.
* And store rules in memory. And we need a component that makes a decision. You may argue whether we need the client identifier builder as a separate component or should it just be a part of the decision-making component. It is up to you. I wanted to present this builder as a separate component to stress the point that client identification is an important step of the whole process. From here interview may go in several directions. Interviewer may be interested in the Rate Limiter algorithm and ask us to implement


Token Bucket Algorithm
==============================

* one. Or interviewer may be interested in object-oriented design and ask us to define main classes and interfaces of the throttling library. Or interviewer may ask us to focus on a distributed throttling solution and discuss how service hosts share data between each other. Let’s discuss each of these possible directions. And start with the algorithm. I will not tell you a secret if I say that there are many different algorithms to solve this problem. You may find inspiration by looking into Google Guava RateLimiter class.

* Or think about how fixed and sliding window paradigms can be applied. But probably the simplest algorithm out there is the Token Bucket algorithm. Let me describe the main idea. The token bucket algorithm is based on an analogy of a bucket filled with tokens. Each bucket has three characteristics: a maximum amount of tokens it can hold, amount of tokens currently available and a refill rate, the rate at which tokens are added to the bucket. Every time request comes, we take a token from the bucket.

* If there are no more tokens available in the bucket, request is rejected. And the bucket is refilled with a constant rate. The beauty of the Token Bucket algorithm is that it simple to understand and simple to implement. Let’s take a look at the code. There are 4 class fields: maximum bucket size, refill rate, number of currently available tokens and timestamp that indicates when bucket was last refilled. Constructor accepts two arguments: maximum bucket size and refill rate. Number of currently available tokens is set to the maximum bucket size.

* And timestamp is set to the current time in nanoseconds. Allow request method has one argument - number of tokens that represent a cost of the operation. Usually, the cost is equal to 1. Meaning that with every request we take a single token from the bucket. But it may be a larger value as well. For example, when we have a slow operation in the web service and each request to that operation may cost several tokens. The first thing we do is refilling the bucket. And right after that we check if there are enough tokens in the bucket.

* In case there are not enough tokens, method return false, indicating that request must be throttled. Otherwise, we need to decrease number of available tokens by the cost of the request. And the last piece is the refill method. It calculates how many tokens accumulated since the last refill and increases currently available tokens in the bucket by this number. Let’s make sure you understand the implementation. Because if you do, it will be easy to implement token bucket algorithm during a real interview.

* Let’s say in time T0 bucket was created. Maximum capacity is set to 10 and refill rate is set to 10 tokens per second. So, the bucket currently has 10 tokens available. In time T1, which is 300 milliseconds later, allow request method call was initiated and the cost of that request is 6 tokens. How many tokens have remained in the bucket after allow request method completed? And the answer is 4. Bucket was full all this time, no new tokens have been added to the bucket. So, we simply subtract 6 tokens.

![img_4.png](img_4.png)


* 200 milliseconds later one more allow request call was initiated. With the 5 tokens cost. How many tokens have remained after this call? And the answer is 1. First, two more tokens have been added to the bucket by the refill method. And then 5 tokens have been subtracted. Easy, right? And 1 second later, actually 900 milliseconds, bucket is full again. So far we have covered the algorithmic part of the rate limiting solution. Let’s take a look at another facet of the problem, which is object-oriented design.

* Let’s define key classes and interfaces. Job Scheduler interface is responsible for scheduling a job that runs every several seconds and retrieves rules from Rules service. RulesCache interface is responsible for storing rules in memory. ClientIdentifier builds a key that uniquely identifies a client. And RateLimiter is responsible for decision making. RetrieveJobScheduler class implements JobScheduler interface. Its responsibility is to instantiate, start and stop the scheduler. And to run retrieve rules task.

* In Java, for example, we can utilize ScheduledExecutorService interface as a scheduler. TokenBucketCache stores token buckets. We can use something simple, for example Map to store buckets. Or utilize 3-rd party cache implementation, like Google Guava cache. ClientIdentifierBuilder is responsible for building a key based on user identity information (for example login). There can be other implementations as well, for example based on IP address. And for the RateLimiter interface lets introduce a TokenBucketRateLimiter class, which is responsible

* for calling allow request on the correspondent bucket for that client. And the last important piece is the RetrieveRulesTask, which is responsible for retrieving all the rules for this service. Let’s look at how these components interact with each other. Hopefully, it will help you to better remember all the components. RetrieveJobScheduler runs RetrieveRulesTask, which makes a remote call to the Rules service. It then creates token buckets and puts them into the cache. When client request comes to the host, RateLimiter first makes a call to the ClientIdentifierBuilder

* to build a unique identifier for the client. And then it passes this key to the cache and retrieves the bucket. And the last step to do is to call allow request on the bucket. Now, let’s step into the distributed world and see how we can make rate limiting work across many machines in a cluster. But let me ask you something first. We have a cluster that consists of 3 hosts. And we want rate limiting solution to allow 4 requests per second for each client. How many tokens should we give to a bucket on every host?

* Should we give 4 divided by 3? And the answer is 4. Each bucket should have 4 tokens initially. The reason for this is that all requests for the same bucket may in theory land on the same host. Load balancers try to distributed requests evenly, but they do not know anything about keys, and requests for the same key will not be evenly distributed. Let's add load balancer into the picture and run a very simple simulation. The first request goes to host A, one token is consumed. The second request goes to host C and one token is consumed there.

![img_5.png](img_5.png)

* Two other requests, within the same 1 second interval, go to host B. And take two tokens from the bucket. All 4 allowed requests hit the cluster; we should throttle all the remaining requests for this second. But we still have tokens available. What should we do? We must allow hosts to talk to each other and share how many tokens they consumed altogether. In this case host A will see that other two hosts consumed 3 tokens. And host A will subtract this number from its bucket. Leaving it with 0 tokens available.

* Host B will find out that A and C consumed two tokens already. Leaving host B with 0 tokens as well. And the same logic applies to host C. Now everything looks correct. 4 requests have been processed and no more requests allowed. I bet you have a question. We gave each bucket 4 tokens. If many requests for the same bucket hit our cluster exactly at the same second. Does this mean that 12 requests may be processed, instead of only 4 allowed? Or may be a more realistic scenario. Because communication between hosts takes time, until all hosts agree on what that final

* number of tokens must be, may there be any requests that slip into the system at that time? Yes. Unfortunately, this is the case. We should expect that sometimes our system may be processing more requests than we expect and we need to scale out our cluster accordingly. By the way, the token bucket algorithm will still handle this use case well. We just need to slightly modify it to allow negative number of available tokens. When 12 requests hit the system, buckets will start sharing this information.

* After sharing, every bucket will have -8 tokens and for the duration of the next 2 seconds all requests will be throttled. So, on average we processed 12 requests within 3 seconds. Although in reality all 12 were processed within the first second. So, communication between hosts is the key. Let’s see how this communication can be implemented. By the way, ideas we will discuss next are applicable not only for rate limiting solution, but many other distributed systems that require data sharing between all hosts in a cluster

* in a real time. The first approach is to tell everyone everything. It means that every host in the cluster knows about every other host in the cluster and share messages with each one of them. You may also heard a term full mesh that describes this network topology. How do hosts discover each other? When a new host is added, how does everyone else know? And there are several approaches used for hosts discovery. One option is to use a 3-rd party service which will listen to heartbeats coming from

* every host. As long as heartbeats come, host is keep registered in the system. If heartbeats stop coming, the service unregister host that is no longer alive. And all hosts in our cluster ask this 3-rd party service for the full list of members. Another option is to resolve some user provided information. For example, user specifies a VIP and because VIP knows about all the hosts behind it, we can use this information to obtain all the members. Or we can rely on a less flexible but still a good option when user provides a list of

* hosts via some configuration file. We then need a way to deploy this file across all cluster nodes every time this list changes. Full mesh broadcasting is relatively straightforward to implement. But the main problem with this approach is that it is not scalable. Number of messages grows quadratically with respect to the number of hosts in a cluster. Approach works well for small clusters, but we will not be able to support big clusters. So, let’s investigate some other options that may require less messages to be broadcasted

* hosts via some configuration file. We then need a way to deploy this file across all cluster nodes every time this list changes. Full mesh broadcasting is relatively straightforward to implement. But the main problem with this approach is that it is not scalable. Number of messages grows quadratically with respect to the number of hosts in a cluster. Approach works well for small clusters, but we will not be able to support big clusters. So, let’s investigate some other options that may require less messages to be broadcasted

* within the cluster. And one such option is to use a gossip protocol. This protocol is based on the way that epidemics spread. Computer systems typically implement this type of protocol with a form of random "peer selection": with a given frequency, each machine picks another machine at random and shares data. By the way, rate limiting solution at Yahoo uses this approach. Next option is to use distributed cache cluster. For example, Redis. Or we can implement custom distributed cache solution. The pros for this approach is that distributed cache cluster is relatively small and our

* service cluster can scale out independently. This cluster can be shared among many different service teams in the organization. Or each team can setup their own small cluster. Next approach also relies on a 3-rd party component. A coordination service that helps to choose a leader. Choosing a leader helps to decrease number of messages broadcasted within the cluster. Leader asks everyone to send it all the information. And then it calculates and sends back the final result. So, each host only needs to talk to a leader or a set of leaders, where each leader is

* responsible for its own range of keys. Consensus algorithms such as Paxos and Raft can be used to implement Coordination Service. Great option, but the main drawback is that we need to setup and maintain Coordination Service. Coordination service is typically a very sophisticated component that has to be very reliable and make sure one and only one leader is elected. But is this really a requirement for our system? Let’s say we use a simple algorithm to elect a leader. But because of the simplicity of the algorithm it may not guarantee one and only one leader.


* So that we may end up with multiple leaders being elected. Is this an issue? Actually, no. Each leader will calculate rate and share with everyone else. This will cause unnecessary messaging overhead, but each leader will have its own correct view of the overall rate. And to finish message broadcasting discussion, I want to talk about communication protocols, how hosts talk to each other. We have two options here: TCP and UDP. TCP protocol guarantees delivery of data and also guarantees that packets will be delivered

* in the same order in which they were sent. UDP protocol does not guarantee you are getting all the packets and order is not guaranteed. But because UDP throws all the error-checking stuff out, it is faster. So, which one is better? Both are good choices. If we want rate limiting solution to be more accurate, but with a little bit of performance overhead, we need to go with TCP. If we ok to have a bit less accurate solution, but the one that works faster, UDP should be our choice. Ok, we have implemented the algorithm, created a set of classes and interfaces, discussed

![img_6.png](img_6.png)

How to integrate all this with the service
=============================================

* message broadcasting. But how do we integrate all this cool solution with the service? Let’s see what options we have. There are two options. And they are pretty standard. We can run Rate Limiter as a part of the service process or as its own process (daemon). In the first option, Rate Limiter is distributed as a collection of classes, a library that should be integrated with the service code. In the second option we have two libraries: the daemon itself and the client, that is responsible for inter-process communication between the service process and the daemon.

![img_7.png](img_7.png)

![img_8.png](img_8.png)


![img_9.png](img_9.png)