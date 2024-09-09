package LLD.LLD002.RateLimiter.TokenBucket;

public class TokenBucketTest {


    public static void main(String[] args) {


        TokenBucket tokenBucket = new TokenBucket(TokenBucketConstants.maxBucketSize, TokenBucketConstants.numberofRequests, TokenBucketConstants.windowSizeForRateLimit);


        int numberOfTokensConsumed = 0;

        long startTime = System.currentTimeMillis();

        long totalTime = 5 * 1000;


        while ((System.currentTimeMillis() - startTime) < totalTime) {

            boolean consumeSuccess = tokenBucket.tryConsume();
            if (consumeSuccess) {
                numberOfTokensConsumed++;
            }
        }


        System.out.println(" number of tokens consumed : " + numberOfTokensConsumed);
        System.out.println(" time taken : " + totalTime);
        System.out.println(" number of requests served per window : " + numberOfTokensConsumed * tokenBucket.windowSizeForRateLimit / totalTime);
        System.out.println(" number of requests per window expected : " + TokenBucketConstants.numberofRequests);

    }
}
