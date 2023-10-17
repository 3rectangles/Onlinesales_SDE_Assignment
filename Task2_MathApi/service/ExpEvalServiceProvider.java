package service;

import models.HttpExpressionResponse;
import models.HttpResponseStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.CompletionStage;

public abstract class ExpEvalServiceProvider {


    protected HttpExpressionResponse handleResponse(HttpURLConnection connection) throws IOException {
        try {
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                // Process the response content
                String responseContent = processResponse(connection);
                return new HttpExpressionResponse(responseCode, responseContent, HttpResponseStatus.SUCCESS);
            } else {
                // Handle non-200 responses by binding the response code to the result
                return new HttpExpressionResponse(responseCode, null, HttpResponseStatus.FAILURE);
            }
        } catch (IOException e) {
            // Throw a runtime exception
            throw new IOException(e);
        }
    }

    private String processResponse(HttpURLConnection connection) throws IOException {
        // Process the API response and return the response content
        // ...
        String content = "";

        return content;

    }

}

