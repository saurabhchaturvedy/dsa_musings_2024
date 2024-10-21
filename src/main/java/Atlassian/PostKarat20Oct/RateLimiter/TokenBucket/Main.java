package Atlassian.PostKarat20Oct.RateLimiter.TokenBucket;

public class Main {


    public static void main(String[] args) {


        TokenBucket tokenBucket = new TokenBucket(TokenBucketConstants.numberOfRequests, TokenBucketConstants.windowSizeForRateLimitInMs, TokenBucketConstants.maxBucketSize);


        long noOfConsumed = 0;

        long startTime = System.currentTimeMillis();


        while ((System.currentTimeMillis() - startTime) < 10 * 1000) {

            boolean isAllowed = tokenBucket.tryConsume();
            System.out.println(" Is request Allowed ? : " + isAllowed);

            if (isAllowed) {
                noOfConsumed++;
            }
        }


        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        System.out.println(" Total time : " + totalTime);
        System.out.println(" Number of Consumed : " + noOfConsumed);
        System.out.println("Number of requests per Window : " + ((noOfConsumed * tokenBucket.windowSizeForRateLimitInMs )/ totalTime));
        System.out.println("Number of expected requests : " + TokenBucketConstants.numberOfRequests);
    }
}
