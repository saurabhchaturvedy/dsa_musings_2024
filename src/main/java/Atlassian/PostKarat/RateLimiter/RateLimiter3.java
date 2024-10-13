package Atlassian.PostKarat.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter3 {


    int maxRequests;
    Map<Integer, Integer> userToRequestCountMap;
    long lastResetTime;
    int windowSizeForRateLimitInSeconds;


    RateLimiter3(int maxRequests, int windowSizeForRateLimitInMs) {

        this.maxRequests = maxRequests;
        this.userToRequestCountMap = new HashMap<>();
        this.lastResetTime = System.currentTimeMillis();
        this.windowSizeForRateLimitInSeconds = windowSizeForRateLimitInMs * 1000;
    }


    public void initializeGlobalReset() {

        long currentTime = System.currentTimeMillis();

        if (currentTime > lastResetTime + windowSizeForRateLimitInSeconds) {

            this.userToRequestCountMap = new HashMap<>();
            lastResetTime = currentTime;
            System.out.println(" Global reset executed ");
        }
    }


    public boolean rateLimit(int customerId) {

        initializeGlobalReset();

        int currentRequestCount = userToRequestCountMap.getOrDefault(customerId, 0);


        if (currentRequestCount < maxRequests) {

            userToRequestCountMap.put(customerId, userToRequestCountMap.getOrDefault(customerId, 0) + 1);
            return true;
        } else {

            return false;
        }
    }


    public static void main(String[] args) throws InterruptedException {


        RateLimiter3 rateLimiter3 = new RateLimiter3(3,1);


        int customerId = 1;

        // Simulate requests
        for (int i = 0; i < 5; i++) { // Attempt to make 5 requests
            boolean allowed = rateLimiter3.rateLimit(customerId);
            System.out.println("Request " + (i + 1) + " allowed: " + allowed);
            Thread.sleep(400); // Wait 400 milliseconds between requests
        }
    }
}
