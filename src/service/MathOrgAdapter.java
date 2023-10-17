package service;

import exception.ApiConnectException;
import models.HttpExpressionResponse;
import models.HttpResponseStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class MathOrgAdapter extends ExpEvalServiceProvider implements ExpressionEvaluationService{
    /**
     * @param exp
     * @return
     */
    private  String apiUrl="";

    @Override
    public CompletableFuture<HttpExpressionResponse> EvaluateExpressionAsync(String exp)  throws RuntimeException {
        Supplier<HttpURLConnection> supplier = () -> {
            try {
                return performApiCall(apiUrl, exp);
            } catch (ApiConnectException e) {
                throw new RuntimeException("Couldnt connect to the API");

            }
        };


        return CompletableFuture.supplyAsync(supplier)
                .thenApply(connection -> {
                    try {
                        return handleResponse(connection);
                    } catch (IOException e) {
                        throw new RuntimeException("Couldnt connect to the API");
                    }
                })
                .exceptionally(ex -> {
                    // Handle exceptions and return a failed response
                    return new HttpExpressionResponse(500,
                            "Error: " + ex.getMessage(), HttpResponseStatus.FAILURE);
                });


    }

    private HttpURLConnection performApiCall(String apiUrl, String expression) throws ApiConnectException{
        try {
            // Build the URL with the expression as a query parameter
            String url = apiUrl + "?expr=" + expression;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            // set parameters

            return  connection;

        } catch (Exception e) {
            // Throw a runtime exception
            throw new ApiConnectException("Error while making the API connection");
        }
    }

}
