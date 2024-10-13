package Atlassian.PostKarat.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter2 {


    int maxRequests;
    Map<Integer, Integer> userToRequestCountMap;


    RateLimiter2(int maxRequests) {

        this.maxRequests = maxRequests;
        this.userToRequestCountMap = new HashMap<>();
    }


    public synchronized boolean rateLimit(int customerId) {
        int requestCount = userToRequestCountMap.getOrDefault(customerId, 0);


        if (requestCount < maxRequests) {
            userToRequestCountMap.put(customerId, userToRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {

            return false;
        }

    }

    public synchronized void reset(int customerId)
    {

        userToRequestCountMap.remove(customerId);
        System.out.println(" Customer ID : "+customerId+" rate limit has been set");
    }


    public static void main(String[] args) {


        RateLimiter2 rateLimiter2 = new RateLimiter2(3);

        int customerId = 1;

        for (int i = 0; i < 5; i++) {

            boolean isAllowed = rateLimiter2.rateLimit(customerId);
            System.out.println(" Request " + (i + 1) + " Allowed ? : " + isAllowed);
        }

        rateLimiter2.reset(customerId);

        for (int i = 0; i < 2; i++) {

            boolean isAllowed = rateLimiter2.rateLimit(customerId);
            System.out.println(" Request " + (i + 1) + " Allowed ? : " + isAllowed);
        }
    }
}
