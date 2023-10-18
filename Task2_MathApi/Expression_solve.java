import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Expression_solve {

    public static void main(String[] args) {
        // Replace with your API endpoint
        String apiEndpoint = "http://api.mathjs.org/v4/";

        try {
            String[] expressions = {
                    "2 * (7 - 3)",
                    "2 / 3",
                    "sqrt(16)",
                    "sin(45 deg)",
                    "log(100)",
                    "2^5",
                    "1 + 2 + 3",
                    " 3! / 2",
                    // Add more expressions here
            };

            String result = evaluateExpressions(apiEndpoint, expressions);
            printResults(expressions, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpressions(String apiEndpoint, String[] expressions) throws Exception {
        URL url = new URL(apiEndpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        // Construct the JSON request using the custom function
        String jsonRequest = createJsonRequest(expressions);

        // Send the request
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.next());
                }
                return response.toString();
            }
        } else {
            // Print the response message for debugging
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }
                System.out.println("API request failed with response code: " + responseCode);
                System.out.println("Error Response: " + errorResponse.toString());
            }
            throw new RuntimeException("API request failed with response code: " + responseCode);
        }
    }

    private static String createJsonRequest(String[] expressions) {
        StringBuilder jsonRequest = new StringBuilder("{ \"expr\": [");
        for (int i = 0; i < expressions.length; i++) {
            jsonRequest.append("\"").append(expressions[i]).append("\"");
            if (i < expressions.length - 1) {
                jsonRequest.append(", ");
            }
        }
        jsonRequest.append("] }");
        return jsonRequest.toString();
    }

    private static void printResults(String[] expressions, String result) {
        // Parse the JSON response
        String[] results = result.split("\"result\":");
        if (results.length > 1) {
            results = results[1].split(",");
            for (int i = 0; i < expressions.length; i++) {
                String expression = expressions[i];
                String expressionResult = results[i].trim();
                // Remove unnecessary characters from the result string
                expressionResult = expressionResult.replaceAll("[\"\\[\\]]", "");
                System.out.println(expression + " => " + expressionResult);
            }
        } else {
            System.out.println("API request failed. No results.");
        }
    }

}
