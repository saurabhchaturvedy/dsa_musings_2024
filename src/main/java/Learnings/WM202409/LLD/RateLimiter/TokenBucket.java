package Learnings.WM202409.LLD.RateLimiter;

public class TokenBucket {


    int numberOfAvailableTokens;
    int numberOfRequests;

    long lastRefillTime;
    long nextRefillTime;


    int windowSizeForRateLimit;
    int maxBucketSize;


    TokenBucket(int maxBucketSize, int numberOfRequests, int windowSizeForRateLimit) {

        this.maxBucketSize = maxBucketSize;
        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimit = windowSizeForRateLimit;
        refill();
    }


    public boolean tryConsume() {

        refill();

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


        lastRefillTime = System.currentTimeMillis();

        nextRefillTime = lastRefillTime + windowSizeForRateLimit;

        numberOfAvailableTokens = Math.max(maxBucketSize, numberOfRequests + numberOfAvailableTokens);
    }
}
