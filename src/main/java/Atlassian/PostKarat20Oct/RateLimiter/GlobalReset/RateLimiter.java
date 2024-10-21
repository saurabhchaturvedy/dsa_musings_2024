package Atlassian.PostKarat20Oct.RateLimiter.GlobalReset;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    public int maxRequests;
    Map<Integer, Integer> userIdToRequestCountMap;
    long lastResetTime;
    long windowSizeForRateLimitInMs;

    RateLimiter(int maxRequests, long windowSizeForRateLimitInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs * 1000;
        this.lastResetTime = System.currentTimeMillis();
        this.userIdToRequestCountMap = new HashMap<>();
    }


    public void initializeGlobalReset() {

        long currentTime = System.currentTimeMillis();

        if (currentTime > lastResetTime + windowSizeForRateLimitInMs) {

            this.userIdToRequestCountMap = new HashMap<>();
            this.lastResetTime = currentTime;
            System.out.println(" Global Reset Initiated");

        }
    }


    public boolean rateLimit(int customerId) {

        initializeGlobalReset();
        int currentCount = userIdToRequestCountMap.getOrDefault(customerId, 0);

        if (currentCount < maxRequests) {

            userIdToRequestCountMap.put(customerId, userIdToRequestCountMap.getOrDefault(customerId, 0) + 1);
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
