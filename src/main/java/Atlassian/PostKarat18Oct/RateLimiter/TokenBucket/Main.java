package Atlassian.PostKarat18Oct.RateLimiter.TokenBucket;

public class Main {


    public static void main(String[] args) {


        TokenBucket tokenBucket = new TokenBucket(TokenBucketConstants.numberOfRequests, TokenBucketConstants.windowSizeForRateLimitInMs, TokenBucketConstants.maxBucketSize);


        int numberOfConsumed = 0;


        long startTime = System.currentTimeMillis();


        while ((System.currentTimeMillis() - startTime) < 10 * 1000) {

            boolean isRequestAllowed = tokenBucket.tryConsume();
            System.out.println(" Is Request Allowed ? : " + isRequestAllowed);

            if (isRequestAllowed) {

                numberOfConsumed++;
            }
        }

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;


        System.out.println(" Number of Consumed = " + numberOfConsumed);
        System.out.println(" Total time = " + totalTime);
        System.out.println("Number of Requests per Window = " + numberOfConsumed * tokenBucket.windowSizeForRateLimitInMs / 1000);
        System.out.println("Number of expected Requests : " + TokenBucketConstants.numberOfRequests);
    }
}
