package Atlassian.PostKarat20Oct.RateLimiter.FixedThreshold;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    public int maxRequests;
    public Map<Integer, Integer> userToRequestCountMap;


    RateLimiter(int maxRequests) {

        this.maxRequests = maxRequests;
        this.userToRequestCountMap = new HashMap<>();
    }


    public boolean rateLimit(int customerId) {

        int currentCount = userToRequestCountMap.getOrDefault(customerId, 0);

        if (currentCount < maxRequests) {

            userToRequestCountMap.put(customerId, userToRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {

            return false;
        }
    }


    public void resetLimit(int customerId) {

        this.userToRequestCountMap.remove(customerId);
        System.out.println(" Rate limit Reset :");
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(3);

        int customerId = 123;

        for (int i = 0; i < 5; i++) {

            boolean isAllowed = rateLimiter.rateLimit(customerId);
            System.out.println(" Request " + (i + 1) + " is allowed ? : " + isAllowed);
        }

        rateLimiter.resetLimit(customerId);

        for (int i = 0; i < 5; i++) {

            boolean isAllowed = rateLimiter.rateLimit(customerId);
            System.out.println(" Request " + (i + 1) + " is allowed ? : " + isAllowed);
        }
    }


}
