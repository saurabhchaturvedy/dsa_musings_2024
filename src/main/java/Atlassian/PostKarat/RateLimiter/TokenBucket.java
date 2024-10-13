package Atlassian.PostKarat.RateLimiter;

public class TokenBucket {


    int numberOfRequests;
    int windowSizeForRateLimitInMs;
    int maxBucketSize;
    int numberOfTokensAvailable;
    long lastRefillTime;
    long nextRefillTime;


    TokenBucket(int numberOfRequests, int windowSizeForRateLimitInMs, int maxBucketSize) {

        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs;
        this.maxBucketSize = maxBucketSize;
        this.refill();
    }


    public boolean tryConsume() {
        this.refill();

        if (this.numberOfTokensAvailable > 0) {
            this.numberOfTokensAvailable--;
            return true;
        }

        return false;
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
