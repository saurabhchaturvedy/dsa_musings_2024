package Atlassian.PostKarat.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class CreditBasedRateLimiting {


    int maxRequests;
    int maxCredits;

    int windowSizeForRateLimitInMs;
    int lastRefreshTime;

    Map<Integer, Integer> userRequestCountMap;
    Map<Integer, Integer> userCreditBalanceMap;
    Map<Integer, Long> userLastTimeRefillLog;


    CreditBasedRateLimiting(int maxRequests, int maxCredits, int windowSizeInSeconds) {

        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
        this.windowSizeForRateLimitInMs = windowSizeInSeconds * 1000;
        this.userRequestCountMap = new HashMap<>();
        this.userCreditBalanceMap = new HashMap<>();
        this.userLastTimeRefillLog = new HashMap<>();
    }


    public boolean rateLimit(int customerId) {

        long currentTime = System.currentTimeMillis();

        long lastRefillTime = userLastTimeRefillLog.getOrDefault(customerId, 0L);


        if (currentTime > lastRefillTime + windowSizeForRateLimitInMs) {


            int unusedRequests = Math.max(0, maxRequests - userRequestCountMap.getOrDefault(customerId, 0));
            int currentCredits = userCreditBalanceMap.getOrDefault(customerId, 0);

            userCreditBalanceMap.put(customerId, Math.min(maxCredits, unusedRequests + currentCredits));


            userRequestCountMap.put(customerId, 0);
            userLastTimeRefillLog.put(customerId, currentTime);

        }


        int currentCount = userRequestCountMap.getOrDefault(customerId, 0);

        if (currentCount < maxRequests) {

            System.out.println(" Rate Limiter ALLOW via maxRequests check");
            userRequestCountMap.put(customerId, userRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {


            int credit = userCreditBalanceMap.getOrDefault(customerId, 0);

            if (credit < maxCredits) {
                System.out.println(" Rate Limiter ALLOW via Credit ");
                userCreditBalanceMap.put(customerId, userCreditBalanceMap.getOrDefault(customerId, 0) - 1);
                return true;
            } else {

                return false;
            }
        }


    }


    public static void main(String[] args) throws InterruptedException {



        CreditBasedRateLimiting rateLimiter = new CreditBasedRateLimiting(3, 5, 5);

        int customerId = 1;


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
