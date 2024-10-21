package Atlassian.PostKarat20Oct.RateLimiter.CreditBased;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    public int maxRequests;
    public int maxCredits;

    public long windowSizeForRateLimitInMs;

    Map<Integer, Integer> userToRequestCountMap;
    Map<Integer, Integer> userToCreditMap;
    Map<Integer, Long> userToLastResetTimeMap;


    RateLimiter(int maxRequests, int maxCredits, long windowSizeForRateLimitInSeconds) {

        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInSeconds * 1000;

        this.userToRequestCountMap = new HashMap<>();
        this.userToCreditMap = new HashMap<>();
        this.userToLastResetTimeMap = new HashMap<>();

    }


    public boolean rateLimit(int customerId) {


        long currentTime = System.currentTimeMillis();

        long lastResetTime = userToLastResetTimeMap.getOrDefault(customerId, 0L);

        if (currentTime > lastResetTime + windowSizeForRateLimitInMs) {

            int unusedRequests = Math.max(0, maxRequests - userToRequestCountMap.getOrDefault(customerId, 0));
            int currentCredits = userToCreditMap.getOrDefault(customerId, 0);

            userToCreditMap.put(customerId, Math.min(maxCredits, unusedRequests + currentCredits));

            System.out.println(" Credit Refresh happening , CREDITS updated = " + userToCreditMap.getOrDefault(customerId, 0));
            userToRequestCountMap.put(customerId, 0);
            userToLastResetTimeMap.put(customerId, currentTime);
        }


        int currentCount = userToRequestCountMap.getOrDefault(customerId, 0);

        if (currentCount < maxRequests) {

            System.out.println(" Using Default Rate Limit :: ");
            userToRequestCountMap.put(customerId, userToRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {


            int currentCredits = userToCreditMap.getOrDefault(customerId, 0);

            if (currentCredits < maxCredits) {

                System.out.println(" Using Credits Remaining  :: " + currentCredits);
                userToCreditMap.put(customerId, userToCreditMap.getOrDefault(customerId, 0) - 1);
                return true;
            } else {
                System.out.println(" Credits Exhausted !!");
                return false;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {


        RateLimiter rateLimiter = new RateLimiter(3, 5, 5);

        int customerId = 123;

        // Simulate requests within the first window
        for (int i = 0; i < 5; i++) {
            boolean allowed = rateLimiter.rateLimit(customerId);
            System.out.println("Request " + (i + 1) + " allowed: " + allowed);
            Thread.sleep(1000); // Wait 1 second between requests
        }

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