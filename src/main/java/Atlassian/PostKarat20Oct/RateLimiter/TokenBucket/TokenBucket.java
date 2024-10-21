package Atlassian.PostKarat20Oct.RateLimiter.TokenBucket;

public class TokenBucket {


    int numberOfRequests;
    int windowSizeForRateLimitInMs;
    int maxBucketSize;

    int numberOfAvailableTokens;
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

        if (this.numberOfAvailableTokens > 0) {
            this.numberOfAvailableTokens--;
            return true;
        }

        return false;

    }

    private void refill() {

        if (System.currentTimeMillis() < this.nextRefillTime)
            return;

        this.lastRefillTime = System.currentTimeMillis();
        this.nextRefillTime = lastRefillTime + windowSizeForRateLimitInMs;

        numberOfAvailableTokens = Math.min(this.maxBucketSize, this.numberOfAvailableTokens + this.numberOfRequests);
    }
}
