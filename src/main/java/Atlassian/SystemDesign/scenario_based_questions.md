

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






Q2:

1. Server Overload at Midnight
With 100,000 machines connecting simultaneously at midnight, the central server could become overwhelmed. This sudden surge of connections could cause server crashes, slow response times, or even lead to dropped connections.

Solution: Implement a staggered or randomized reporting schedule. For example, instead of all machines connecting at exactly midnight, you could randomize the connection time over a window (e.g., between 12:00 AM and 12:30 AM) to distribute the load more evenly.

2. Single Point of Failure
If you are relying on a single central server, any downtime, network failure, or server overload could prevent machines from reporting their statuses. This would result in lost data and could delay restocking and maintenance operations.

Solution: Use a distributed architecture with multiple servers in different regions. You could use a load balancer to distribute incoming connections across servers, ensuring redundancy and avoiding a single point of failure.

3. Network Latency and Failures
Since the machines are spread across different cities and rely on cellular networks, there’s a risk that some machines might not be able to connect due to poor signal, network congestion, or latency, especially during high-traffic times (like midnight).

Solution: Add a retry mechanism for machines that fail to connect. The machines could retry after a short delay (with an exponential backoff strategy) if they fail to send the status report at midnight. You might also allow machines to report at alternative times if midnight is a busy time for the cellular network.

4. Database Overload
If 100,000 machines are reporting at the same time, your database could experience a large influx of writes, potentially overwhelming it and leading to performance bottlenecks.

Solution: Implement a queueing system like RabbitMQ or Kafka to handle incoming reports and process them asynchronously. This way, the machines can send their status updates quickly, and the central server can process these updates in batches without overloading the database.

5. Scalability of the Batch Job
With a large number of machines, your 1 AM batch job could take a long time to process the maintenance and restocking schedules, especially if the database is large and not optimized for batch operations.

Solution: Instead of running a single monolithic batch job, consider parallelizing the job by splitting the workload across multiple threads or nodes. You can also investigate incremental or continuous processing (i.e., processing reports as they come in) instead of waiting for a batch job.

6. Time Zone Considerations
Since the machines are located in various cities around the world, the definition of "midnight" varies by time zone. If all machines report at midnight local time, you could end up with a rolling surge of status updates as midnight happens in each time zone.

Solution: Either handle this rolling wave by ensuring the server can handle the continuous load or synchronize reporting to occur at the same universal time (e.g., midnight UTC).

7. Security and Authentication
Since these vending machines will be connected over the internet via cellular networks, security is a concern. Unauthorized access could compromise the machine data or allow malicious actors to alter reports or schedules.

Solution: Use secure communication protocols such as TLS and implement strong authentication mechanisms for each machine when it connects to the central server.

By addressing these potential issues, you can build a more robust and scalable system that can handle the large number of machines and ensure the timely and reliable processing of their status updates.