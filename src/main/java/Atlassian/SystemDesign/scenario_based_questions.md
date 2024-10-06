

Q1 :

1. Limit the Number of Concurrent Threads
Instead of starting a new thread for every video, implement a thread pool or limit the number of concurrent threads (e.g., use a maximum of 5-10 threads). You can use a queue to hold the video requests and only process a limited number of them at once. For example, in Java, you could use ExecutorService to control the thread count:

java
Copy code
ExecutorService executor = Executors.newFixedThreadPool(5); // Limit to 5 threads
for (Video video : videoList) {
    executor.submit(() -> processVideo(video));
}
executor.shutdown();
2. Move to a Task Queue System
Use a task queue like RabbitMQ, Kafka, or Redis to manage the video processing requests. This way, the video generation tasks can be queued, and you can control how many are processed concurrently. The system can scale across multiple machines more easily, preventing overload on a single server.

3. Use Rate Limiting
Temporarily impose rate limiting on the service, ensuring users cannot upload too many videos at the same time. You can return a friendly message to users that there’s a high volume of requests, and they should try again later.

4. Vertical Scaling
If possible, temporarily scale up the machine by adding more CPU and memory, which can help handle more load until you solve the problem.

5. Implement Video Processing Delays
Introduce a short delay between the processing of each video to reduce the sudden surge of CPU usage when processing many videos simultaneously.

6. Move to a Separate Machine
Run the video processing service on a separate machine to avoid affecting other processes. This won’t prevent the service from crashing, but it will isolate the issue, allowing other applications to run without interference.

These workarounds can help you continue running the service while minimizing crashes. Which option sounds most feasible for your system?







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
