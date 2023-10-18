package service;

import exception.ApiConnectException;
import models.HttpExpressionResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExpressionEvaluationService {
    public CompletableFuture<HttpExpressionResponse> EvaluateExpressionAsync(List<String> expressions) throws ApiConnectException;

}
