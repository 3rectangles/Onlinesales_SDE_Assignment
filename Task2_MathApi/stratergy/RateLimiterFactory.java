package stratergy;

public class RateLimiterFactory {
    static RateLimiter createRateLimitClass( String type, int reqpersec) throws IllegalAccessException {
        if( type.equals("token_bucket"))
            return new TokenBucket(reqpersec);
        else
            throw  new IllegalAccessException(" not a correct conecrete class of rate limiter");
    }
}
