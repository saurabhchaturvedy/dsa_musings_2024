

# Q1 : Workarounds for Video Subtitle Generation Service

## Problem Overview
The service that generates subtitles for users' videos starts a new thread for every video. This process is processor-intensive and causes the server to crash when processing more than 10 videos at a time. Currently, the service runs as a single process on a single machine, and the crash leads to losing all requests and affects other processes on the machine. Fixing this bug may take time, so the following workarounds are proposed to continue running the service.

---

## Workarounds

### 1. Limit the Number of Concurrent Threads
**Problem:**  
Starting a new thread for every video results in high CPU and memory usage, leading to crashes.

**Solution:**
- Implement a thread pool to limit the number of concurrent threads.
- Queue the video processing tasks and process a limited number at a time (e.g., 5-10 threads), and process the remaining videos as earlier ones complete.

**Java Example (using `ExecutorService`):**
```java
ExecutorService executor = Executors.newFixedThreadPool(5); // Limit to 5 threads
for (Video video : videoList) {
    executor.submit(() -> processVideo(video));
}
executor.shutdown();








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
