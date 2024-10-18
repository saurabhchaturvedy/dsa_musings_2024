package Atlassian.PostKarat18Oct.RateLimiter.TokenBucket;

public class TokenBucketConstants {


    public static int numberOfRequests = 10;
    public static int windowSizeForRateLimitInMs = 1 * 1000;
    public static int maxBucketSize = 10;
}
