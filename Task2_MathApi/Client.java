import models.HttpExpressionResponse;
import models.HttpResponseStatus;
import service.MathOrgAdapter;
import stratergy.RateLimiter;
import stratergy.RateLimiterFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {

    public static void main(String[] args) {
        MathOrgAdapter adapter = new MathOrgAdapter();

        String[] expressions = {
                "2 * (7 - 3)",
                "2 / 3",
                "sqrt(16)",
                "sin(45 deg)",
                "log(100)",
                "2^5",
                "1 + 2 + 3",
                "3! / 2"
                // Add more expressions here
        };

        List<String> expressionsList = Arrays.asList(expressions);
        //System.out.println("Size of input: " + expressionsList.size());

        // Create a rate limiter
        RateLimiter rateLimiter = null;
        try {
            rateLimiter = RateLimiterFactory.createRateLimitClass("token_bucket", 100);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        Queue<List<String>> expressionQueue = new ConcurrentLinkedQueue<>();
        expressionQueue.offer(expressionsList);

        while (!expressionQueue.isEmpty()) {
            List<String> currentExpressions = expressionQueue.poll();

            // Use the rate limiter before making the request
            if (rateLimiter != null) {
                while (!rateLimiter.isAllowed()) {
                    try {
                        Thread.sleep(10); // Wait for 10 milliseconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            CompletableFuture<HttpExpressionResponse> future = adapter.EvaluateExpressionAsync(currentExpressions);

            future.thenAccept(httpExpressionResponse -> {
                if (httpExpressionResponse.getStatus() == HttpResponseStatus.SUCCESS) {
                    List<String> results = httpExpressionResponse.getResponseContent();
                    for (int i = 0; i < currentExpressions.size(); i++) {
                        String expression = currentExpressions.get(i);
                        String result = results.get(i);
                        System.out.println(expression + " => " + result);
                    }
                } else {
                    System.out.println("API request failed. No results.");
                }
            }).exceptionally(ex -> {
                        System.err.println("Error: " + ex.getMessage());
                        return null;
                    });

                    // You can add more expressions to the queue here if needed

            // Wait for the future to complete, wont be needed when server is used
            future.join();
        }



    }
}
