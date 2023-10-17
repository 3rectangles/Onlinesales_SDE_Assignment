package models;

public class HttpExpressionResponse {
    private int responseCode;
    private String responseContent;
    private HttpResponseStatus status = HttpResponseStatus.Pending;

    public HttpResponseStatus getStatus() {
        return status;
    }

    public HttpExpressionResponse(int responseCode, String responseContent, HttpResponseStatus status) {
        this.responseCode = responseCode;
        this.responseContent = responseContent;
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseContent() {
        return responseContent;
    }
}
