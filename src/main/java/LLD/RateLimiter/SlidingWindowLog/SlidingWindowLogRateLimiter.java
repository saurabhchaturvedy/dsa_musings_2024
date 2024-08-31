package LLD.RateLimiter.SlidingWindowLog;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter {


    int maxRequests;
    int windowSizeForRateLimit;
    Map<String, LinkedList<Long>> userRequestLogs;


    SlidingWindowLogRateLimiter(int maxRequests, int windowSizeForRateLimit) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimit = windowSizeForRateLimit;
        this.userRequestLogs = new ConcurrentHashMap<>();
    }


    public boolean allowRequest(String userId) {


        long currentTime = System.currentTimeMillis();


        userRequestLogs.putIfAbsent(userId, new LinkedList<>());

        LinkedList<Long> timeStamps = userRequestLogs.get(userId);


        while (!timeStamps.isEmpty() && (currentTime - timeStamps.peekFirst()) > windowSizeForRateLimit) {

            timeStamps.pollFirst();
        }


        if (timeStamps.size() < maxRequests) {

            timeStamps.addLast(currentTime);
            return true;
        } else {


            return false;
        }
    }


    public static void main(String[] args) throws InterruptedException {


        SlidingWindowLogRateLimiter slidingWindowLogRateLimiter = new SlidingWindowLogRateLimiter(5, 10000);


        String userId = "User_Id";

        for (int i = 0; i < 10; i++) {

            if (slidingWindowLogRateLimiter.allowRequest(userId)) {

                System.out.println(" request " + (i + 1) + " is allowed");
            } else {

                System.out.println(" request " + (i + 1) + " is rejected");
            }

            Thread.sleep(1000);
        }
    }
}
