package Atlassian.PostKarat18Oct.RateLimiter.FixedThreshold;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    Map<Integer, Integer> userToRequestCountMap;


    RateLimiter(int maxRequests) {

        this.maxRequests = maxRequests;
        this.userToRequestCountMap = new HashMap<>();
    }


    public void resetLimit(int customerId) {

        this.userToRequestCountMap.remove(customerId);
        System.out.println(" Customer " + customerId + " Reset");
    }


    public boolean rateLimit(int customerId) {

        int requestCount = userToRequestCountMap.getOrDefault(customerId, 0);

        if (requestCount < this.maxRequests) {

            userToRequestCountMap.put(customerId, userToRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {

            return false;
        }
    }


    public static void main(String[] args) {

        RateLimiter rateLimiter = new RateLimiter(5);

        int customerId = 123;

        for (int i = 0; i < 10; i++) {

            boolean isRequestAllowed = rateLimiter.rateLimit(customerId);

            System.out.println(" Request : " + (i + 1) + " is allowed : " + isRequestAllowed);
        }

        rateLimiter.resetLimit(customerId);


        for (int j = 0; j < 10; j++) {

            boolean isRequestAllowed = rateLimiter.rateLimit(customerId);

            System.out.println(" Request : " + (j + 1) + " is allowed : " + isRequestAllowed);
        }
    }

}
