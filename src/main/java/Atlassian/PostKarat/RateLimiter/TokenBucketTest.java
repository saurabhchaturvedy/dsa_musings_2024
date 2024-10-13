package Atlassian.PostKarat.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenBucketTest {

    public static void main(String[] args) {
        // Create a TokenBucket with a limit of 5 requests per window of 1000 ms, max bucket size of 10
        TokenBucketThreadSafe tokenBucket = new TokenBucketThreadSafe(10, 1000, 10);

        // Create a thread pool with 10 threads
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Simulate 20 requests
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                // Each thread tries to consume a token
                if (tokenBucket.tryConsume()) {
                    System.out.println(Thread.currentThread().getName() + " consumed a token.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not consume a token.");
                }
            });
        }

        // Shutdown the executor service gracefully
        executorService.shutdown();
    }
}
