


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