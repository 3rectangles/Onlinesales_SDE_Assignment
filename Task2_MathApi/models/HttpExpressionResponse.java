package models;

import java.util.List;

public class HttpExpressionResponse {
    private int responseCode;
    private List<String> expressionResults;
    private HttpResponseStatus status = HttpResponseStatus.Pending;

    public HttpResponseStatus getStatus() {
        return status;
    }

    public HttpExpressionResponse(int responseCode, List<String> expressionResults, HttpResponseStatus status) {
        this.responseCode = responseCode;
        this.expressionResults = expressionResults;
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<String> getResponseContent() {
        return expressionResults;
    }
}
