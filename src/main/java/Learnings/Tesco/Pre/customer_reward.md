package Learnings.Tesco.Pre;# Customer Reward System Design

## Problem Overview

The system receives customer orders from a Kafka Topic containing customer ID and order amount. For every ₹100 spent, a customer earns 1 reward point. Based on the accumulated reward points, customers are ranked. The system provides two REST endpoints:

- **Endpoint 1**: Returns the top N customers by rank.
- **Endpoint 2**: Returns the rank and reward points of a specific customer, along with the +2 and -2 ranked neighbors.

### Assumptions
- **Throughput**: 1000 requests/messages per second.
- **Scalability**: System needs to handle millions of customers.
- **Resilience**: Fault tolerance and scalability are critical, not cost efficiency.
- **Tech Stack**: Open-source technologies are preferred over public cloud solutions.

---

## System Components

### 1. **Kafka - Message Ingestion**
Kafka is used to ingest customer orders with the following properties:
- **Customer ID**: Unique identifier for the customer.
- **Order Amount**: Amount spent on the order.

**Partitioning Strategy**:
- Partition Kafka topics by customer ID for scalability and even workload distribution.
- Use Kafka's replication and exactly-once semantics to ensure fault tolerance and message consistency.

---

### 2. **Stream Processing - Real-Time Reward Calculation**
A stream processor (e.g., **Apache Flink** or **Kafka Streams**) continuously consumes orders from Kafka and processes them:
- **Calculates reward points**: 1 point per ₹100 spent.
- **Updates customer state** in a distributed state store (e.g., **Redis** or **Cassandra**).

**State Management**:
- Stream processing tasks maintain customer reward points in a local or external state store.
- Utilize **RocksDB** for embedded state or **Redis** as an external state store.
- Periodic backups using Kafka changelog topics ensure fault tolerance.

**Scalability**:
- Stream processing is horizontally scalable by increasing task instances.
- Each task handles a subset of customers using Kafka partitioning.

---

### 3. **Batch Processing - Rank Calculation**
Recalculating customer ranks for millions of customers after every order is computationally expensive. Instead, ranks are recalculated periodically (e.g., every minute).

**Batch Job**:
- Fetches customer reward points from the state store (Redis/Cassandra).
- Recalculates ranks and stores them in a **sorted set** in Redis for fast rank lookup.

**Redis Sorted Set**:
- `ZADD`: Adds customer reward points to the sorted set.
- **Top N customers** can be retrieved quickly using Redis sorted sets (`ZREVRANGE`).

---

### 4. **State Store - Redis or Cassandra**
A distributed state store is used for storing customer reward points and ranks. Two possible options are:

- **Redis**: 
  - Low-latency, high-throughput key-value store.
  - Supports sorted sets (`ZSET`) for storing ranks.
  
- **Cassandra**:
  - A distributed NoSQL database that can handle large-scale data.
  - Stores both customer reward points and rank-related information.

**Data Model**:
- **Customer ID → Reward Points**.
- **Customer ID → Rank**.
- Use a Redis sorted set to store and efficiently query ranks.

---

### 5. **REST API - Querying Customer Data**
Two endpoints are exposed for querying customer reward data.

**Endpoints**:
1. `/top-customers?N=<value>`: Retrieves the top N customers based on their rank.
   - Query Redis for the top N customers using `ZREVRANGE` from the sorted set.
   
2. `/customer-info?id=<customerID>`: Retrieves rank and reward points of a specific customer, along with the +2 and -2 ranked neighbors.
   - Use Redis to query the customer's rank using `ZRANK`.
   - Fetch neighboring ranks using `ZRANGE` operations.

---

### 6. **Scalability and Resilience**
- **Horizontal Scaling**: Kafka consumers, stream processors, and the state store can all be scaled horizontally.
- **Fault Tolerance**: Kafka’s replication and the stream processor’s state management ensure fault tolerance.
- **Redis/Cassandra Clustering**: Distributed state stores can handle large volumes of data and support high availability.

---

## System Sequence Flow

1. **Order Ingestion**:
   - A customer order is ingested via Kafka.
   - Kafka partitions messages based on customer ID to enable parallel processing.

2. **Stream Processing**:
   - The stream processor consumes messages from Kafka.
   - The reward points for the customer are calculated and updated in Redis/Cassandra.
   
3. **Batch Job for Rank Calculation**:
   - Periodically, a batch job recalculates customer ranks.
   - Updated ranks are stored in a Redis sorted set for quick lookup.

4. **REST API Query**:
   - API queries fetch data from Redis sorted sets for top N customers or customer rank and neighbor information.

---

## Scalability and Resilience Features

- **Kafka**: 
  - Replication and partitioning ensure scalability and fault tolerance.
  
- **Stream Processing**:
  - Horizontal scaling ensures that the system can process 1000 messages per second.
  - Exactly-once semantics prevent data duplication.

- **Redis/Cassandra**:
  - Both state stores are distributed and scalable, ensuring low-latency access to millions of customers’ data.

- **REST API**:
  - Load balancers and multiple instances of the API ensure high availability and low latency during traffic spikes.

---

## Additional Considerations

- **Data Consistency**: 
  - Achieve eventual consistency for rank recalculations.
  - Reward points are updated in real-time, but ranks are recalculated periodically.

- **Caching**:
  - Cache top N customer ranks and frequently queried customer data to reduce repeated expensive queries.

- **Latency vs. Throughput**:
  - Real-time reward calculation needs low latency.
  - Rank recalculation can tolerate slightly higher latency, but it's done efficiently in batches.

---

## Technologies Used
- **Apache Kafka**: For message ingestion and partitioning.
- **Apache Flink/Kafka Streams**: For real-time stream processing.
- **Redis/Cassandra**: For storing customer reward points and ranks.
- **REST API**: For exposing customer rank and reward data.
