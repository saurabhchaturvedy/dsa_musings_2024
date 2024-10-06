# Q4 - Storage Estimation

## Overview
We are running a simple photo storage and sharing service where users upload their photos to our servers and share links with others to view them. Instead of using a cloud service, we manage our own server farms. The goal is to create an estimate of the storage required over the coming year and the associated costs.

## Information Needed

1. **Current Usage Metrics:**
    - Average number of photos uploaded per user per day.
    - Total number of active users currently using the service.
    - Average size of a photo (in MB or GB).

2. **Growth Projections:**
    - Expected user growth rate (how many new users you expect to onboard each month).
    - Estimated increase in the average number of photos uploaded per user over time.

3. **Retention Policy:**
    - How long will you keep the photos (e.g., indefinitely, a certain period, etc.)?
    - Any policies regarding deleting unused or old photos.

4. **Photo Type Distribution:**
    - Breakdown of photo types (e.g., JPEG, PNG, RAW) as different formats have varying sizes.
    - Estimates of any video uploads, if applicable.

5. **Backup Requirements:**
    - Backup and redundancy strategies (e.g., 1x, 2x, or 3x redundancy) for data integrity and availability.
    - Frequency of backups (daily, weekly).

6. **Server Configuration:**
    - The current configuration of the server hardware, including total storage capacity and performance specifications.
    - Any anticipated upgrades or changes to the infrastructure over the year.

7. **Cost of Storage:**
    - Current costs associated with storage (e.g., per GB) based on your server infrastructure.
    - Potential costs for additional servers or upgrades to meet projected storage needs.

## Factors to Consider

1. **User Behavior:**
    - Seasonal trends in photo uploads (e.g., holidays, events) that could impact storage needs.

2. **Performance Requirements:**
    - How fast you need to access and retrieve photos, which might affect the choice of storage solution (e.g., SSD vs. HDD).

3. **Scaling Strategy:**
    - Strategies for scaling storage as demand increases (e.g., adding new servers, increasing existing server capacities).

4. **Infrastructure Maintenance Costs:**
    - Costs associated with maintaining and managing your own server farm (e.g., personnel, hardware maintenance, power, cooling).

5. **Security Considerations:**
    - Measures for securing the data and protecting against loss, which may require additional storage for backups.

6. **User Experience:**
    - Impact of storage and retrieval times on user experience, which could influence decisions regarding architecture and technology.

## Estimation Process

1. **Calculate Total Storage Needs:**
    - Use the current metrics and growth projections to estimate total storage needs for the year:
      \[
      \text{Total Storage} = \text{Current Photos} + (\text{New Users} \times \text{Avg. Photos/User/Year} \times \text{Avg. Size of Photo})
      \]

2. **Factor in Growth:**
    - Adjust for user growth and retention policies to get an annual estimate.

3. **Calculate Cost:**
    - Multiply the total storage estimate by the cost per GB to determine overall storage costs for the year.

4. **Include Redundancy Costs:**
    - Consider redundancy costs based on your backup strategy (if applicable).

5. **Review and Adjust:**
    - Review the estimates regularly, especially as user behavior and system usage patterns change.
