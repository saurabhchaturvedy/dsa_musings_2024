package Atlassian.PostKarat20Oct.RateLimiter.FixedWindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {


    public int maxRequests;
    public long windowSizeForRateLimitInMs;
    public Map<Integer, Queue<Long>> userIdToTimeStampsMap;


    RateLimiter(int maxRequests, long windowSizeForRateLimitInSeconds) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInSeconds * 1000;
        this.userIdToTimeStampsMap = new HashMap<>();
    }


    public boolean rateLimit(int customerId) {

        long currentTime = System.currentTimeMillis();

        Queue<Long> timestamps = userIdToTimeStampsMap.getOrDefault(customerId, new LinkedList<>());


        long previousTimeStamp = timestamps.peek() == null ? 0 : timestamps.peek();


        long timeDifference = currentTime - previousTimeStamp;
        System.out.println("Time Difference " + timeDifference + " milli seconds ");
        System.out.println("Window Size " + windowSizeForRateLimitInMs + " milli seconds ");

        if (!timestamps.isEmpty() && timeDifference > windowSizeForRateLimitInMs) {

            timestamps.poll();
        }

        System.out.println("Timestamp size : " + timestamps.size());
        if (timestamps.size() < maxRequests) {

            timestamps.add(currentTime);
            userIdToTimeStampsMap.put(customerId, timestamps);
            return true;
        } else {

            return false;
        }

    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(3, 5);


        int customerId = 123;

        for (int i = 0; i < 10; i++) {

            boolean isAllowed = rateLimiter.rateLimit(customerId);
            System.out.println(" Request " + (i + 1) + " is allowed ? : " + isAllowed);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
