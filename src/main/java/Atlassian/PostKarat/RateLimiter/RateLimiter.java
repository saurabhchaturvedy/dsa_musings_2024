package Atlassian.PostKarat.RateLimiter;

import java.util.HashMap;

public class RateLimiter {
    private final int X; // max requests allowed
    private final int Y; // time window in seconds
    private final HashMap<Integer, CustomerRequestInfo> customerRequests = new HashMap<>();

    public RateLimiter(int maxRequests, int timeWindow) {
        this.X = maxRequests;
        this.Y = timeWindow;
    }

    public boolean rateLimit(int customerId) {
        long currentTime = System.currentTimeMillis() / 1000; // get current time in seconds

        CustomerRequestInfo customerInfo = customerRequests.getOrDefault(customerId, new CustomerRequestInfo(0, currentTime));

        // Check if current time is within the same window
        if (currentTime - customerInfo.startTime >= Y) {
            // New time window: reset request count and start time
            customerInfo.startTime = currentTime;
            customerInfo.requestCount = 1; // first request in the new window
        } else {
            // Still within the same window: increment request count
            if (customerInfo.requestCount < X) {
                customerInfo.requestCount++;
            } else {
                // Rate limit exceeded
                return false;
            }
        }

        customerRequests.put(customerId, customerInfo);
        return true;
    }

    // Helper class to store customer request data
    private static class CustomerRequestInfo {
        int requestCount;
        long startTime;

        public CustomerRequestInfo(int requestCount, long startTime) {
            this.requestCount = requestCount;
            this.startTime = startTime;
        }
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(5, 10); // max 5 requests per 10 seconds
        
        int customerId = 1;
        // Simulate some requests

        for(int i=0; i<20; i++) {
            System.out.println(rateLimiter.rateLimit(customerId)); // true
        }

    }
}
