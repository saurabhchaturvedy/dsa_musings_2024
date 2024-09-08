


Count videos/likes on instagram

Calculate application performance metrics


Why requirement clarification is so important ?


Count Views on You-tube


SQL/No-SQL
Batch Streaming


Requirement Clarification

* Users -  who will use the system , how it will be used
* Scale (Read and Write) - How many read queries per second , how much data queried per request , How many videos are processed per second, spikes in traffic ?
* Performance - what is expected write to read delay , expected p99 latency
* Cost - Should cost be minimized to develop system , maintain system ?


Functional Requirements

1. System has to count video view events

countViewEvent(videoId)
countEvent(videoId,eventType)
processEvent(videoId,eventType Function)
processEvents(events)
getViewsCount(videoId,startTime,endTime)


Non-Functional Requirements
=============================


1. Scalable 10000/views per second
2. Highly Performant - few ms to return the data
3. Highly Available - highly available all the time
4. Consistency


High Level Architecture
==============================

![alt-text](https://github.com/saurabhchaturvedy/dsa_musings_2024/blob/0c2666b0fd14487131451d59bea85e2244dfd0a2/src/main/java/Learnings/WM202409/SystemDesign/Images/HLD.png "optional-title")



What we store ?

Video ID

TimeStamp

Store Individual Data
Store Aggregate Data

![alt-text](https://github.com/saurabhchaturvedy/dsa_musings_2024/blob/7f66f555e475444c70a29b6cb45592e41c3810fe/src/main/java/Learnings/WM202409/SystemDesign/Images/data_store.png "optional-title")


1. Stream Data Processing
2. Batch Data Processing


Where we store ?
================

![alt-text](https://github.com/saurabhchaturvedy/dsa_musings_2024/blob/59e08873dd58e01228f9f05a94998e401e177ecc/src/main/java/Learnings/WM202409/SystemDesign/Images/where_we_store.png "optional-title")

SQL Database - MySQL
Split Data into partitions - Sharding 

Make services talk to Database using cluster proxy

![alt-text](https://github.com/saurabhchaturvedy/dsa_musings_2024/blob/7f66f555e475444c70a29b6cb45592e41c3810fe/src/main/java/Learnings/WM202409/SystemDesign/Images/data_store.png "optional-title")


How cluster proxy will know healthy and unhealthy instances ?

Configuration service (Zookeeper)

![img.png](img.png)



Benefits of Shard Proxy

1. Cache Query Results
2. Publish Metrics
3. Terminate Queries that take too long to return the data
4. Monitor Data Base Instance Health

We need to replicate data. Let's call each existing shard a master shard or a leader. And for every master shard we introduce a copy of it, called read replica or a follower. We call it read replica because writes still go through a master shard, but reads may go through both master shard and a replica. We also put some replicas to a data center different from their master shard. So that if the whole data center goes down, we still have a copy of data available. So, when store data request comes, based on the information provided by Configuration



NO SQL Data Base (Cassandra)
============================

We have all these proxies, configuration service, leader and replica instances. May be we can simplify things a little bit. Let's take a look at what NoSQL databases can offer us. And more specifically, Apache Cassandra database. In NoSQL world we also split data into chunks - shards, also known as nodes. But instead of having leaders and followers we say that each shard is equal. We no longer need configuration service to monitor health of each shard. Instead, let's allow shards talk to each other and exchange information about their state.

To reduce network load, we do not need each shard to talk to every other shard. Every second shard may exchange information with a few other shards, no more than 3. Quickly enough state information about every node propagates throughout the cluster. This procedure is called a gossip protocol. Ok, each node in the cluster knows about other nodes. And this is a big deal. Remember that previously we used Cluster Proxy component to route requests to a particular shard. As Cluster Proxy was the only one who knew about all shards.