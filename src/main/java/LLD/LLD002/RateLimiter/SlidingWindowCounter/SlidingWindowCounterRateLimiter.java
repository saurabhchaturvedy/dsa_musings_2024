package LLD.LLD002.RateLimiter.SlidingWindowCounter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter {


    public int maxRequests;
    public int windowSizeForRateLimit;
    public int bucketSizeForRateLimit;

    Map<String, UserBucket> userBucketMap;


    SlidingWindowCounterRateLimiter(int maxRequests, int windowSizeForRateLimit, int bucketSizeForRateLimit) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimit = windowSizeForRateLimit;
        this.bucketSizeForRateLimit = bucketSizeForRateLimit;
        this.userBucketMap = new ConcurrentHashMap<>();
    }


    public boolean allowRequest(String userId) {


        long currentTime = System.currentTimeMillis();


        userBucketMap.putIfAbsent(userId, new UserBucket());

        UserBucket userBucket = userBucketMap.get(userId);

        userBucket.updateCounts(currentTime, windowSizeForRateLimit, bucketSizeForRateLimit);


        int totalRequestCount = userBucket.totalRequestCount();

        if (totalRequestCount < maxRequests) {

            userBucket.incrementCurrentBucketIndex();
            return true;
        } else {

            return false;
        }
    }


    public static class UserBucket {


        int[] counters;
        long lastUpdateTime;

        int currentBucketIndex = 0;


        UserBucket() {

            this.counters = new int[60];
            this.lastUpdateTime = System.currentTimeMillis();
            this.currentBucketIndex = 0;
        }


        public void updateCounts(long currentTime, int windowSizeForRateLimit, int bucketSizeForRateLimit) {

            long timeElapsed = currentTime - lastUpdateTime;
            int bucketsElapsed = (int) timeElapsed / bucketSizeForRateLimit;

            if (bucketsElapsed > 0) {

                for (int i = 0; i < Math.min(bucketsElapsed, counters.length); i++) {

                    counters[(currentBucketIndex + i + 1) % counters.length] = 0;
                }


                currentBucketIndex = (currentBucketIndex + bucketsElapsed) % counters.length;
                lastUpdateTime = currentTime;
            }
        }


        public int totalRequestCount() {

            int total = 0;

            for (int x : counters) {

                total += x;
            }

            return total;
        }


        public void incrementCurrentBucketIndex() {

            counters[currentBucketIndex]++;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(5, 60000, 1000); // 5 requests per 1 minute window, 1 second sub-interval

        String userId = "user1";
        for (int i = 0; i < 10; i++) {
            if (rateLimiter.allowRequest(userId)) {
                System.out.println("Request " + (i + 1) + " is allowed.");
            } else {
                System.out.println("Request " + (i + 1) + " is blocked.");
            }
            Thread.sleep(1000); // Simulate a delay between requests
        }
    }
}
