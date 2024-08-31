package LLD.RateLimiter;

public class TokenBucket {


    int numberofAvailableTokens;
    int numberofRequests;

    long lastRefillTime;
    long nextRefillTime;

    int windowSizeForRateLimit;
    int maxBucketSize;


    TokenBucket(int maxBucketSize, int numberofRequests, int windowSizeForRateLimit) {

        this.maxBucketSize = maxBucketSize;
        this.numberofRequests = numberofRequests;
        this.windowSizeForRateLimit = windowSizeForRateLimit;
        this.refill();
    }


    public boolean tryConsume() {

        this.refill();

        if (numberofAvailableTokens > 0) {

            numberofAvailableTokens--;
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
        numberofAvailableTokens = Math.min(maxBucketSize, numberofRequests + numberofAvailableTokens);
    }
}
