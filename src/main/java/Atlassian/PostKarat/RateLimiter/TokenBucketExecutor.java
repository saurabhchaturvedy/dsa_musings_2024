package Atlassian.PostKarat.RateLimiter;

public class TokenBucketExecutor {


    public static void main(String[] args) {


        TokenBucket tokenBucket = new TokenBucket(TokenBucketConstants.numberOfRequests, TokenBucketConstants.windowSizeForRateLimitInMs, TokenBucketConstants.maxBucketSize);

        int numberOfConsumed = 0;

        long startTime = System.currentTimeMillis();


        while ((System.currentTimeMillis() - startTime) < 10 * 1000) {

            boolean isRequestedAllowed = tokenBucket.tryConsume();

            System.out.println(" Rate Limiter Response : " + isRequestedAllowed);

            if (isRequestedAllowed) {
                System.out.println(" REQUEST ALLOWED :::");
                numberOfConsumed++;
            }
        }

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;


        System.out.println(" Number of consumed : " + numberOfConsumed);
        System.out.println("total time : " + totalTime);
        System.out.println("Number of requests per window : " + numberOfConsumed * tokenBucket.windowSizeForRateLimitInMs / totalTime);
        System.out.println("Number of requests expected : " + tokenBucket.numberOfRequests);

    }

}
