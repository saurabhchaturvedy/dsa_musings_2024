package Atlassian.PostKarat18Oct.RateLimiter.TokenBucket;

public class TokenBucket {


    public int numberOfRequests;
    public int windowSizeForRateLimitInMs;
    public int maxBucketSize;

    long lastRefillTime;
    long nextRefillTime;

    int numberOfAvailableTokens;


    TokenBucket(int numberOfRequests, int windowSizeForRateLimitInMs, int maxBucketSize) {

        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs;
        this.maxBucketSize = maxBucketSize;
        this.refill();
    }


    public boolean tryConsume() {

        this.refill();

        if (numberOfAvailableTokens > 0) {

            numberOfAvailableTokens--;
            return true;
        }


        return false;
    }


    public void refill() {

        if (System.currentTimeMillis() < nextRefillTime) {
            return;
        }


        this.lastRefillTime = System.currentTimeMillis();
        this.nextRefillTime = this.lastRefillTime + this.windowSizeForRateLimitInMs;

        this.numberOfAvailableTokens = Math.max(maxBucketSize, this.numberOfAvailableTokens + this.numberOfRequests);


    }
}
