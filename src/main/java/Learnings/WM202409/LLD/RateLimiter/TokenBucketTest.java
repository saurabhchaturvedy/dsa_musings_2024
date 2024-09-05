package Learnings.WM202409.LLD.RateLimiter;

public class TokenBucketTest {


    public static void main(String[] args) {


        TokenBucket tokenBucket = new TokenBucket(TokenBucketConstants.maxBucketSize, TokenBucketConstants.numberOfRequests, TokenBucketConstants.windowSizeForRateLimit);


        int numberOfTokensConsumed = 0;

        long startTime = System.currentTimeMillis();

        long totalTime = 5 * 1000;


        while ((System.currentTimeMillis() - startTime) < totalTime) {

            boolean tryConsume = tokenBucket.tryConsume();
            if (tryConsume) {
                numberOfTokensConsumed++;
            }
        }


        System.out.println(" No of total tokens consumed : " + numberOfTokensConsumed);
        System.out.println(" total time = " + totalTime);
        System.out.println(" Number of requests processed per window " + numberOfTokensConsumed * tokenBucket.windowSizeForRateLimit / 1000);
        System.out.println(" number of request " + TokenBucketConstants.numberOfRequests);
    }
}
