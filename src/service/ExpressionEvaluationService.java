package service;

import exception.ApiConnectException;
import models.HttpExpressionResponse;

import java.util.concurrent.CompletableFuture;

public interface ExpressionEvaluationService {
    public CompletableFuture<HttpExpressionResponse> EvaluateExpressionAsync(String exp) throws ApiConnectException;

}
