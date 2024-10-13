package Atlassian.PostKarat.RateLimiter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketThreadSafe {



    int numberOfRequests;
    int windowSizeForRateLimitInMs;
    int maxBucketSize;
    int numberOfTokensAvailable;
    long lastRefillTime;
    long nextRefillTime;


    private final Lock lock = new ReentrantLock();

    TokenBucketThreadSafe(int numberOfRequests, int windowSizeForRateLimitInMs, int maxBucketSize) {

        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs;
        this.maxBucketSize = maxBucketSize;
        this.refill();
    }


    public boolean tryConsume() {
        lock.lock(); // Acquire the lock
        try {
            this.refill();

            if (this.numberOfTokensAvailable > 0) {
                this.numberOfTokensAvailable--;
                return true;
            }
            return false;
        } finally {
            lock.unlock(); // Ensure the lock is released
        }
    }


    private void refill() {


        if (System.currentTimeMillis() < nextRefillTime) {
            return;
        }

        lastRefillTime = System.currentTimeMillis();
        nextRefillTime = this.lastRefillTime + windowSizeForRateLimitInMs;
        numberOfTokensAvailable = Math.min(maxBucketSize, this.numberOfTokensAvailable + this.numberOfRequests);
    }
}
