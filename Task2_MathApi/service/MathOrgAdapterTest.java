package service;
import models.HttpExpressionResponse;
import models.HttpResponseStatus;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class MathOrgAdapterTest {

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
        System.out.println( " size of input " + expressionsList.size());

        CompletableFuture<HttpExpressionResponse> future = adapter.EvaluateExpressionAsync(expressionsList);

        future.thenAccept(httpExpressionResponse -> {
            if (httpExpressionResponse.getStatus() == HttpResponseStatus.SUCCESS) {
                List<String> results = httpExpressionResponse.getResponseContent();
                for (int i = 0; i < expressions.length; i++) {
                    String expression = expressions[i];
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

        future.join();
    }
}

