package Atlassian.PostKarat18Oct.RateLimiter.CreditBased;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    int maxCredits;

    int windowSizeForRateLimitInMs;

    Map<Integer, Integer> userRequestCountMap;
    Map<Integer, Integer> userRequestCreditMap;
    Map<Integer, Long> userLastResetTimeLog;


    RateLimiter(int maxRequests, int maxCredits, int windowSizeForRateLimitInSeconds) {

        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInSeconds * 1000;

        this.userRequestCountMap = new HashMap<>();
        this.userRequestCreditMap = new HashMap<>();
        this.userLastResetTimeLog = new HashMap<>();
    }


    public boolean rateLimit(int customerId) {

        long currentTime = System.currentTimeMillis();

        long lastResetTime = userLastResetTimeLog.getOrDefault(customerId, 0L);


        if (currentTime > lastResetTime + windowSizeForRateLimitInMs) {

            int unusedCredits = Math.max(0, maxRequests - userRequestCountMap.getOrDefault(customerId, 0));
            int currentCredits = userRequestCreditMap.getOrDefault(customerId, 0);

            userRequestCreditMap.put(currentCredits, Math.min(maxCredits, unusedCredits + currentCredits));


            userRequestCountMap.put(customerId, 0);
            userLastResetTimeLog.put(currentCredits, currentTime);
        }


        int requestCount = userRequestCountMap.getOrDefault(customerId, 0);

        if (requestCount < maxRequests) {

            System.out.println(" Request count = "+requestCount);
            System.out.println(" Rate Limiting via Request Limit Quota");
            userRequestCountMap.put(customerId, requestCount + 1);
            return true;
        } else {


            int creditsAvailable = userRequestCreditMap.getOrDefault(customerId, 0);

            if (creditsAvailable < maxCredits) {
                System.out.println(" Rate Limiting via Credits Quota");
                userRequestCreditMap.put(customerId, userRequestCreditMap.getOrDefault(customerId, 0) - 1);
                return true;
            } else {

                return false;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {


        RateLimiter rateLimiter = new RateLimiter(3, 5, 10);

        int customerId = 123;
        for (int i = 0; i < 5; i++) {
            boolean allowed = rateLimiter.rateLimit(customerId);
            System.out.println("Request " + (i + 1) + " allowed: " + allowed);
            Thread.sleep(1000); // Wait 1 second between requests
        }


        System.out.println("simulating sleep");
        // Simulate some idle time to accumulate credits (after 5 seconds)
        Thread.sleep(6000);

        // Simulate more requests after accumulating credits
        for (int i = 0; i < 5; i++) {
            boolean allowed = rateLimiter.rateLimit(customerId);
            System.out.println("Request " + (i + 6) + " allowed: " + allowed);
            Thread.sleep(1000); // Wait 1 second between requests
        }
    }
}
