# Q4 - Sharding Design Review

## Overview
For our photo storage service, we are considering sharding the photos and associated data evenly onto multiple machines based on usernames. The proposed scheme divides usernames alphabetically, assigning each starting letter to a different server. For example, if we had 26 servers, usernames starting with 'A' would go to Server 1, those starting with 'B' to Server 2, and so on.

### Potential Problems with the Design

1. **Uneven Distribution of Users**
    - **Skewed Data Distribution:** The distribution of usernames is not uniform, with some letters having significantly more usernames (e.g., 'S', 'J', 'M'), leading to overloaded servers while others are underutilized.

2. **User Growth and Rebalancing**
    - **Difficulty in Rebalancing:** New users may cluster around certain letters, causing one or two servers to become overloaded. Rebalancing can be complex and time-consuming, especially if it requires moving large amounts of data.

3. **Single Point of Failure**
    - **Server Dependency:** If a server goes down (e.g., Server 19 for usernames starting with 'S'), it affects all users in that category, leading to potential service outages. High availability strategies are essential to mitigate this risk.

4. **Performance Bottlenecks**
    - **Increased Latency:** Overloaded servers can lead to increased latency in data retrieval and processing, negatively impacting overall performance.

5. **Lack of Flexibility**
    - **Fixed Shard Boundaries:** The rigid alphabetic scheme lacks adaptability to changing usage patterns. New popular username prefixes can lead to overloaded shards without a simple redistribution mechanism.

6. **Complexity in User Management**
    - **User Migration:** Changes in usernames or sharding policies may necessitate complex and error-prone data migrations across servers.

7. **Difficulties with Aggregation Queries**
    - **Cross-Server Queries:** Operations requiring data from multiple servers can complicate querying, leading to performance hits and increased complexity in managing those queries.

8. **Potential for Data Hotspots**
    - **Frequent Access Patterns:** Popular usernames (e.g., celebrities) may create hotspots on specific servers, leading to performance degradation.

### Recommendations
To address these issues, consider the following strategies:

- **Hash-Based Sharding:** Use a hashing algorithm to distribute users more evenly across servers, improving load balancing.
- **Dynamic Resharding:** Implement dynamic resharding to redistribute users based on load, usage patterns, or specific growth thresholds.
- **Monitoring and Alerts:** Utilize monitoring tools to track server usage and set up alerts for hotspots or bottlenecks.
- **Load Balancers:** Incorporate load balancers to distribute incoming requests based on server load, further mitigating potential bottlenecks.

## Conclusion
By recognizing and addressing these concerns, we can enhance the performance and reliability of our photo storage service as it scales.
