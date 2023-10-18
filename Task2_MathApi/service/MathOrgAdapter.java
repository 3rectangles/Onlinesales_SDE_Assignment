package service;

import exception.ApiConnectException;
import models.HttpExpressionResponse;
import models.HttpResponseStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class MathOrgAdapter  implements ExpressionEvaluationService{
    /**
     * @param exp
     * @return
     */
    private  String apiUrl="http://api.mathjs.org/v4/";

    @Override
    public CompletableFuture<HttpExpressionResponse> EvaluateExpressionAsync(List<String> exp)  throws RuntimeException {
        Supplier<HttpExpressionResponse> supplier = () -> {
            try {
                return performApiCall(apiUrl, exp);
            } catch (ApiConnectException e) {
                throw new RuntimeException("Couldnt connect to the API");

            }
        };


        return CompletableFuture.supplyAsync(supplier)
                .exceptionally(ex -> {
                    // Handle exceptions and return a failed response
                    return new HttpExpressionResponse(500,
                            null, HttpResponseStatus.FAILURE);
                });


    }

    private HttpExpressionResponse performApiCall(String apiEndpoint, List<String> expressions) throws ApiConnectException {
        try {
            // Construct the URL with the expressions
            String apiUrlWithExpressions = apiEndpoint;
            URL url = new URL(apiUrlWithExpressions);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Construct the JSON request using the custom function
            String jsonRequest = createJsonRequest(expressions);

            // Send the request
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequest.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Handle the API response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNext()) {
                        response.append(scanner.next());
                    }
                    //System.out.println(responseCode);

                    List<String> answers = responseParser(response.toString());
                    return new HttpExpressionResponse(responseCode,answers,HttpResponseStatus.SUCCESS);
                }
            } else {
                return new HttpExpressionResponse(responseCode,null,HttpResponseStatus.FAILURE);
            }
        } catch (IOException e) {
            throw new ApiConnectException("Error while making the API connection");
        }
    }

    private static String createJsonRequest(List<String> expressions) {
        StringBuilder jsonRequest = new StringBuilder("{ \"expr\": [");
        for (int i = 0; i < expressions.size(); i++) {
            jsonRequest.append("\"").append(expressions.get(i)).append("\"");
            if (i < expressions.size() - 1) {
                jsonRequest.append(", ");
            }
        }
        jsonRequest.append("] }");
        return jsonRequest.toString();
    }

//    private static List<String> responseParser(String response) {
//        List<String> results = new ArrayList<>();
//        String[] responseParts = response.split("\"result\":");
//        if (responseParts.length > 1) {
//            String resultPart = responseParts[1].trim();
//            resultPart = resultPart.substring(1, resultPart.length() - 2); // Remove surrounding quotes and brackets
//            String[] expressionResults = resultPart.split("\",\"");
//            for (String expressionResult : expressionResults) {
//                // Remove unnecessary characters from the result string
//                expressionResult = expressionResult.replaceAll("[\"\\[\\]]", "");
//                results.add(expressionResult);
//            }
//        }
//        return results;
//    }


    private static List<String> responseParser(String response) {
        List<String> results = new ArrayList<>();
        String[] responseParts = response.split("\"result\":");
        if (responseParts.length > 1) {
            String resultPart = responseParts[1].trim();
            resultPart = resultPart.substring(1, resultPart.length() - 2); // Remove surrounding quotes and brackets
            String[] expressionResults = resultPart.split("\",\"");
            for (int i = 0; i < expressionResults.length; i++) {
                // Remove unnecessary characters from the result string
                String expressionResult = expressionResults[i].replaceAll("[\"\\[\\]]", "");
                if (i == expressionResults.length - 1) {
                    int commaIndex = expressionResult.indexOf(',');
                    if (commaIndex != -1) {
                        expressionResult = expressionResult.substring(0, commaIndex);
                    }
                }
                results.add(expressionResult);
            }
        }
        return results;
    }






}
