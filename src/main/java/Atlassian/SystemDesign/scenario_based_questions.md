

# Q1 : Workarounds

## Overview
This service generates subtitles for users' videos. It starts a new thread for every video, which is processor-intensive. When processing more than 10 videos simultaneously, the service crashes the server, losing all requests in progress and affecting other processes. While the root cause of the bug is investigated, here are several workarounds to keep the service running.

---

## Workarounds

### 1. Limit the Number of Concurrent Threads
**Problem:**  
Starting a new thread for each video leads to excessive resource usage.

**Solution:**
- Implement a **thread pool** to limit the number of concurrent threads.
- Use a queue to manage video processing tasks, allowing only a set number of tasks to run concurrently (e.g., 5-10). When one task completes, the next task in the queue can start.

---

### 2. Use a Task Queue
**Problem:**  
Overloading the server with too many simultaneous threads.

**Solution:**
- Implement a **task queue** (e.g., RabbitMQ, Redis, Kafka) to manage incoming video processing requests.
- Only process a limited number of tasks at a time, queuing additional requests until resources are available.

---

### 3. Implement Rate Limiting
**Problem:**  
Excessive concurrent requests can lead to server crashes.

**Solution:**
- Introduce **rate limiting** to control the number of requests processed simultaneously.
- If the server is busy, reject additional requests or inform users to try again later.

---

### 4. Vertical Scaling (Increase Server Resources)
**Problem:**  
Insufficient server resources cause crashes.

**Solution:**
- Temporarily **increase server resources** (e.g., adding more CPU cores or RAM) to accommodate higher loads.
- This can provide immediate relief until a permanent fix is found.

---

### 5. Stagger Video Processing Tasks
**Problem:**  
Overwhelming the server with simultaneous processing.

**Solution:**
- Introduce delays between starting video processing tasks to **stagger** the workload.
- For example, start a new task every few seconds instead of all at once.

---

### 6. Isolate the Service on a Separate Machine or Container
**Problem:**  
Crashes in the service affect other processes.

**Solution:**
- Run the subtitle generation service on a **dedicated machine** or within a **container** (e.g., Docker) to prevent interference with other processes.
- This isolates the service and limits its impact on the overall system.

---

### 7. Batch Processing
**Problem:**  
Overloading the server with simultaneous requests.

**Solution:**
- Implement **batch processing** to group video requests and process them at specified intervals, rather than handling each request immediately.
- This approach can help manage server load effectively.

---

## Conclusion
These workarounds aim to stabilize the subtitle generation service while the root cause of the issue is being resolved. By managing thread concurrency, utilizing task queues, implementing rate limiting, and considering resource scaling, you can help ensure that the service continues to operate effectively during this period.








## Q2 :Potential Issues and Solutions

### 1. Server Overload at Midnight
**Problem:**  
With 100,000 machines connecting simultaneously at midnight, the central server may be overwhelmed, leading to server crashes, slow responses, or dropped connections.

**Solution:**
- Implement a staggered or randomized reporting schedule.  
  Example: Machines connect between 12:00 AM and 12:30 AM, instead of all at once.

### 2. Single Point of Failure
**Problem:**  
Relying on a single central server introduces the risk of downtime, which could prevent machines from reporting their statuses.

**Solution:**
- Implement a distributed architecture with multiple servers.
- Use a load balancer to distribute traffic across these servers, ensuring redundancy.

### 3. Network Latency and Failures
**Problem:**  
Cellular network issues, such as poor signal or congestion, could prevent some machines from successfully sending their status updates.

**Solution:**
- Introduce a retry mechanism with exponential backoff for failed connections.
- Allow machines to report during alternative times if the network is congested at midnight.

### 4. Database Overload
**Problem:**  
A large influx of writes from 100,000 machines may overwhelm the database, affecting performance.

**Solution:**
- Use a message queue (e.g., RabbitMQ, Kafka) to buffer incoming reports and process them asynchronously.
- Process status updates in batches to avoid database bottlenecks.

### 5. Scalability of the Batch Job
**Problem:**  
A monolithic batch job could take a long time to process the restocking and maintenance schedules for a large number of machines.

**Solution:**
- Parallelize the batch job by splitting the workload across multiple threads or nodes.
- Consider processing reports incrementally as they come in, rather than in a single batch.

### 6. Time Zone Considerations
**Problem:**  
Machines located in different cities will have varying local midnight times, creating a rolling surge of status updates.

**Solution:**
- Handle the rolling surge by ensuring the server can manage continuous traffic.
- Alternatively, synchronize reporting to a common universal time (e.g., midnight UTC).

### 7. Security and Authentication
**Problem:**  
Connecting machines via the internet poses security risks, such as unauthorized access or tampering with data.

**Solution:**
- Use secure communication protocols like TLS.
- Implement strong authentication for each machine before it connects to the server.

---

## Conclusion
By addressing these potential issues, the system can become more scalable, reliable, and secure, allowing it to handle a large number of vending machines efficiently and effectively.
