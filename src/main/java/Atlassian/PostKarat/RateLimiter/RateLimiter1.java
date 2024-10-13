package Atlassian.PostKarat.RateLimiter;

import LLD.LLD002.RateLimiter.FixedWindowCounter.RateLimiter;
import LLD.LLD003.MovieBookingSystem.Theatre;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter1 {


    int maxRequests;
    long windowSizeForRateLimitInMs;
    Map<Integer, Queue<Long>> userTimeStamps;


    RateLimiter1(int maxRequests, long windowSizeForRateLimitInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs * 1000;
        this.userTimeStamps = new HashMap<>();
    }


    synchronized boolean rateLimit(int customerId) {
        long currentTime = System.currentTimeMillis();
        Queue<Long> timestamps = userTimeStamps.getOrDefault(customerId, new LinkedList<>());


        System.out.println(" Current Time : "+currentTime);
        System.out.println(" Queue Timestamp Peek Value : "+timestamps.peek());
        System.out.println("Time difference = "+(currentTime - (timestamps.peek()==null ? 0 : timestamps.peek())));
        System.out.println(" Queue size : "+timestamps.size());
        System.out.println("Window Size For Rate Limit : "+windowSizeForRateLimitInMs);

        long timeStampDifference = currentTime - (timestamps.peek()==null ? 0 : timestamps.peek());
        while (!timestamps.isEmpty() && timeStampDifference > windowSizeForRateLimitInMs) {
            System.out.println(" Polling Queue :");
            timestamps.poll();
        }

        if (timestamps.size() < maxRequests) {

            timestamps.add(currentTime);
            userTimeStamps.put(customerId, timestamps);
            return true;
        } else {

            return false;
        }


    }


    public static void main(String[] args) throws InterruptedException {


        RateLimiter1 rateLimiter1 = new RateLimiter1(3, 10);


//        for (int i = 0; i < 10; i++) {
//
//            boolean isAllowed = rateLimiter1.rateLimit(1);
//            System.out.println(" Request " + (i + 1) + " Is Allowed ? " + isAllowed);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//                Thread.currentThread().interrupt();
//            }
//        }



        RateLimiter1 rateLimiter2 = new RateLimiter1(3, 10); // 3 requests per 10 seconds
        int customerId = 1;

        // Simulate requests
        for (int i = 0; i < 10; i++) { // Attempt to make 5 requests
            boolean allowed = rateLimiter2.rateLimit(customerId);
            System.out.println("Request " + (i + 1) + " allowed: " + allowed);
            Thread.sleep(2000); // Wait 2 seconds between requests to simulate time passing
        }


        // Simulate additional requests after the time window has elapsed
        Thread.sleep(8000); // Wait enough time to reset the rate limit
        for (int i = 0; i < 2; i++) { // Attempt to make 2 more requests
            boolean allowed = rateLimiter2.rateLimit(customerId);
            System.out.println("Request " + (i + 6) + " allowed: " + allowed);
        }
    }
}
