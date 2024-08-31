package LLD.RateLimiter.FixedWindowCounter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {


    int maxRequests;
    int windowSizeForRateLimit;

    Map<String, AtomicInteger> userRequestCount;
    Map<String, Long> userLastRequestTime;


    RateLimiter(int maxRequests, int windowSizeForRateLimit) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimit = windowSizeForRateLimit;
        this.userRequestCount = new ConcurrentHashMap<>();
        this.userLastRequestTime = new ConcurrentHashMap<>();
    }


    public boolean allowRequest(String userId) {


        long currentTime = System.currentTimeMillis();


        userRequestCount.putIfAbsent(userId, new AtomicInteger(0));
        userLastRequestTime.putIfAbsent(userId, currentTime);

        long lastRequestTime = userLastRequestTime.get(userId);

        if (currentTime - lastRequestTime > windowSizeForRateLimit) {

            userRequestCount.get(userId).set(0);
            userLastRequestTime.put(userId, currentTime);
        }


        long currentCount = userRequestCount.get(userId).incrementAndGet();

        if (currentCount > maxRequests) {

            return false;
        }

        return true;
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 1000);


        String userId = "User_1";

        for (int i = 0; i < 10; i++) {

            if (rateLimiter.allowRequest(userId)) {

                System.out.println(" request " + (i + 1) + " is allowed");
            } else {

                System.out.println(" request " + (i + 1) + " is rejected");
            }
        }
    }
}
