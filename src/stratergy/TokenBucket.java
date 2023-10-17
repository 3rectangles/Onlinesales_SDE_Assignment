package stratergy;

public class TokenBucket  implements RateLimiter{
    /**
     * @return
     */

    private long lastRequestTime;
    private int requestsPerSecond;
    private int tokens;
    private final Object lock = new Object();

    public TokenBucket(int requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
        this.tokens = requestsPerSecond;
        this.lastRequestTime = System.currentTimeMillis();
    }


    @Override
    public boolean isAllowed() {
        synchronized (lock) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastRequestTime;

            // Calculate the number of tokens to add based on elapsed time
            int tokensToAdd = (int) (elapsedTime * requestsPerSecond / 1000);

            // Refill tokens if elapsed time has passed
            if (tokensToAdd > 0) {
                tokens = Math.min(requestsPerSecond, tokens + tokensToAdd);
                lastRequestTime = currentTime;
            }

            // Check if a request is allowed
            if (tokens > 0) {
                tokens--;
                return true;
            } else {
                return false;
            }
        }
    }

}
