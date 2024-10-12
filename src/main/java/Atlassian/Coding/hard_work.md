

Many people have interviews with common themes of problems being asked. However, it is often unclear whether certain questions should be answered as part of **System Design** or **Coding Design**.

For example, an **API Rate Limiter** can be coded simply, but it can also become complex if scaling is involved. Below, I categorize some common interview questions into System Design and Coding Design.

## System Design Questions

1. **API Rate Limiter**  
   Video resource: [API Rate Limiter System Design](https://www.youtube.com/watch?v=FU4WlwfS3G0&ab_channel=SystemDesignInterview)

2. **Design a Tagging System**  
   Example: Tagging files and finding the top N tags.

3. **Photo Storage Service**

4. **Game Machine with Payment System**

5. **Design REST API**  
   This seems more like API design, part of system design.

6. **Web Crawler**

7. **Design Color Picker**

8. **System for Keeping Track of User Statuses**

9. **Design a File System**  
   Example: Given files with size and collection information, design a system that generates a summary of total size per collection for the top k collections.

   Additional Reference:  
   [Code Design - File Processing Scenario](https://leetcode.com/discuss/interview-question/1508471/code-design-file-processing-scenario)

10. **Tagging System Design**
    - No code required, just explain the design.

## Coding Design Questions

1. **Snake Game**  
   Resources: Practice with numpy and 2D NDarray for coordinates.

2. **Tagging Files and Finding Top N Tags**  
   Given a set of files, find the total size of all files and the top N collections.

3. **Popularity Tracking Service**  
   Implement a service that tracks the popularity of a given set of IDs (representing content) and provides a method to determine the most popular content.  
   Related problem: [All O'One Data Structure](https://leetcode.com/problems/all-oone-data-structure/description/)

4. **API Rate Limiter**  
   Implement a rate limiter that ensures a customer can make X requests per Y seconds.

5. **Voting System**  
   Build a system that handles a stream of votes and determines the winning candidate based on a rank-based voting system.

6. **Sub-List Finder**  
   Implement a method that finds the starting index where a second list occurs as a sub-list in the first list.

7. **Knapsack Problem**  
   Solve a variant of the knapsack problem using coding techniques.

## Resources and Additional Reading

- [Atlassian Interview Experience (Sydney)](https://leetcode.com/discuss/interview-experience/4133665/Atlassian-P6-or-Sydney-or-Rejected-offer)
- [Atlassian Interview Questions on Geeks for Geeks](https://www.geeksforgeeks.org/atlassian-interview-questions-for-technical-profiles/)

## Atlassian Interview Structure

- **Backend Coding**  
  Example: Implement the base logic of a game (like Snake Game). This interview focuses on building something, not solving Leetcode-style problems.

- **Backend System Design**  
  Assessing large-scale system design decisions.

- **Leadership Craft Interview**  
  Focuses on how outcomes-driven you are, your ability to collaborate, and how you improve team/organizational practices.

- **Values Interview**  
  Consists of 5 questions, with one question per value Atlassian focuses on.
