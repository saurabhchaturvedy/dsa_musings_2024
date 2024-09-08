


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

