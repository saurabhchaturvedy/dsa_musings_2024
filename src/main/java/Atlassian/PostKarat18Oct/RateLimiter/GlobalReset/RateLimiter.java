package Atlassian.PostKarat18Oct.RateLimiter.GlobalReset;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    public int maxRequests;

    Map<Integer, Integer> userToRequestCountMap;

    long lastResetTime;

    int windowSizeForRateLimitInMs;


    RateLimiter(int maxRequests, int windowSizeForRateLimitInSeconds) {

        this.maxRequests = maxRequests;
        this.userToRequestCountMap = new HashMap<>();
        this.lastResetTime = System.currentTimeMillis();
        this.windowSizeForRateLimitInMs = 10 * 1000;
    }


    public void initializeGlobalReset() {

        long currentTime = System.currentTimeMillis();


        if (currentTime > lastResetTime + windowSizeForRateLimitInMs) {

            this.userToRequestCountMap = new HashMap<>();
            this.lastResetTime = currentTime;
            System.out.println(" Global Reset Initialized");
        }
    }


    public boolean rateLimit(int customerId) {

        initializeGlobalReset();
        int requestCount = userToRequestCountMap.getOrDefault(customerId, 0);

        if (requestCount < maxRequests) {

            userToRequestCountMap.put(customerId, userToRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {

            return false;
        }
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);

        for (int i = 0; i < 10; i++) {

            boolean isRequestAllowed = rateLimiter.rateLimit(123);

            System.out.println(" Request : " + (i + 1) + " is allowed ?? " + isRequestAllowed);
        }

        for (int i = 0; i < 10; i++) {

            boolean isRequestAllowed = rateLimiter.rateLimit(123);

            System.out.println(" Request : " + (i + 1) + " is allowed ?? " + isRequestAllowed);
        }


    }

}
