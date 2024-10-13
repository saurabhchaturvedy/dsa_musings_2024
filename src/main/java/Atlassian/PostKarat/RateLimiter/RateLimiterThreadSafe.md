# Concurrent Credit-Based Rate Limiting System

## Overview

This system implements a **credit-based rate limiter** to control customer request rates. Each customer has a limit on the number of requests they can make per time window, and unused capacity accumulates as credits. When a customer exceeds their limit, credits are consumed. Once credits are exhausted, the customer is rate-limited.

The system is scalable and designed to handle concurrent traffic. For distributed environments, the state can be shared across instances using a distributed cache or database.

## Key Features

- **Credit-Based System**: Customers can accumulate credits when they make fewer requests than allowed, providing more flexibility for bursty traffic.
- **Concurrency-Safe**: Supports concurrent access through thread-safe data structures.
- **Scalable**: Designed to handle distributed traffic using a centralized state store (e.g., Redis or distributed databases).

## How it Works

1. **Rate Limit and Credits**:
    - Each customer has a maximum number of requests (`maxRequests`) they can make in a given time window (`timeWindow`).
    - If a customer doesn’t use their full request limit, unused requests are converted into credits.
    - Credits can be accumulated up to a maximum (`maxCredits`).
    - When the customer exceeds the request limit, credits are consumed. If credits are exhausted, the customer is rate-limited.

2. **Thread Safety**:
    - A `ConcurrentHashMap` is used to store request counts, credits, and the last request timestamp per customer.
    - `AtomicInteger` ensures that request counts and credits are updated atomically without requiring locks for every operation.
    - A `ReentrantLock` is used for operations that require global resets or time window updates.

3. **Distributed Environment**:
    - In a distributed environment, local memory can't be used to share state across instances.
    - A distributed cache like **Redis** can be used to share customer request counts and credits across servers using atomic operations (`INCR`, `DECR`).
    - Alternatively, distributed databases like **Cassandra** or **DynamoDB** can be used to store request counts and credits.

## Scaling for Distributed Systems

### 1. **Use Redis for Distributed Rate Limiting**

To scale across multiple servers, we can replace the in-memory `ConcurrentHashMap` with **Redis**, which allows sharing state across distributed instances. Here’s the process:

- **Request Count**: Use Redis `INCR` to atomically increment the customer’s request count.
- **Credits**: Use Redis `GET`, `DECR`, and `SET` to manage credits.
- **Expiration**: Use Redis `EXPIRE` to reset the request count after the time window ends.

### 2. **Distributed Database Option**

Alternatively, you can use a distributed database like **Cassandra** or **DynamoDB** to store and manage request counts and credits. These databases provide low-latency access and eventual consistency, making them suitable for rate-limiting scenarios in distributed systems.

## Optimizations for High Traffic

1. **Batch Updates**: To avoid frequent Redis/database writes, batch request count updates in memory and push them periodically to Redis or the database.
2. **Load Balancing**: Use a load balancer to distribute requests evenly across rate-limiting servers.
3. **Sharding**: Partition customers across multiple Redis instances or database nodes to avoid performance bottlenecks.
