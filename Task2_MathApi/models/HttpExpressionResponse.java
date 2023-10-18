package models;

import java.util.List;

public class HttpExpressionResponse {
    private int responseCode;
    private List<String> expressionResults;
    private HttpResponseStatus status = HttpResponseStatus.Pending;

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<String> getExpressionResults() {
        return expressionResults;
    }

    public void setExpressionResults(List<String> expressionResults) {
        this.expressionResults = expressionResults;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }

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
