package stratergy;

public class RateLimiterFactory {
   public static RateLimiter createRateLimitClass( String type, int reqpersec) throws IllegalAccessException {
        if( type.equals("token_bucket"))
            return new TokenBucket(reqpersec);
        else
            throw  new IllegalAccessException(" not a correct concrete class of rate limiter");
    }
}
