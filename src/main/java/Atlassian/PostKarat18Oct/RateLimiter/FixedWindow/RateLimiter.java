package Atlassian.PostKarat18Oct.RateLimiter.FixedWindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {


    int maxRequests;
    int windowSizeForRateLimitInMs;
    Map<Integer, Queue<Long>> userTimeStampMap;


    RateLimiter(int maxRequests, int windowSizeForRateLimitInSeconds) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInSeconds * 1000;
        this.userTimeStampMap = new HashMap<>();
    }

    public boolean rateLimit(int customerId) {

        long currentTime = System.currentTimeMillis();

        Queue<Long> timestamps = userTimeStampMap.getOrDefault(customerId, new LinkedList<>());


        long lastTimeStamp = timestamps.peek() == null ? 0 : timestamps.peek();
        long timeDifference = currentTime - lastTimeStamp;

        while (!timestamps.isEmpty() && timeDifference > windowSizeForRateLimitInMs) {

            timestamps.poll();
        }


        if (timestamps.size() < maxRequests) {

            timestamps.add(currentTime);
            userTimeStampMap.put(customerId, timestamps);
            return true;
        } else {

            return false;
        }

    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);

        int customerId = 123;

        for (int i = 0; i < 10; i++) {

            boolean isAllowed = rateLimiter.rateLimit(customerId);
            System.out.println(" Request " + (i + 1) + " is Allowed ? : " + isAllowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
