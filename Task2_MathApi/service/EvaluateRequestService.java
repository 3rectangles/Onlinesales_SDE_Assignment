package service;
// gets the request id, fetches it using RequestRepository, check for null pointer when interactign with Repository
// loops over the expression of each request
// calls the 3rd party API to evaluate the result ( concurrently)
// blocks the main thread for all expression to complete
// uodates the result of each expression according to the response of completable future
// save updates expression using (ExpressionRepository)
// returns list of expression ans

// this list is taken by controller and converted to EvaluateReqResponseDto (map of expression vs ans)
// client gets the responseDTO, checks the reponsestatus if fullfilled prints on the console else ERROR print


import exception.ApiConnectException;
import models.HttpExpressionResponse;
import models.HttpResponseStatus;
import models.Request;
import repositories.RequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EvaluateRequestService {
    private  RequestRepository requestRepository;
    private  ExpressionEvaluationService adapter =  ExpressionEvaluationServiceFactory.createApiAdapter("math.js");
    public EvaluateRequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }



    public Request createRequest(List<String> expressions) {
        // Create a Request object
        Request req = new Request();

        // Update the expressions in the Request object
        req.setExpressions(expressions);

        // Save the Request object in the repository
        requestRepository.save(req);

        return req;
    }

    public List<String> evaluateExpression(List<String> expressions) throws ApiConnectException  {

        List<String> answers = new ArrayList<>();
        CompletableFuture<HttpExpressionResponse> future = adapter.EvaluateExpressionAsync(expressions);
        future.thenAccept(httpExpressionResponse -> {
            if (httpExpressionResponse.getStatus() == HttpResponseStatus.SUCCESS) {
                List<String> results = httpExpressionResponse.getResponseContent();
            }
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });


        // Wait for the future to complete, wont be needed when server is used
        answers = future.join().getExpressionResults();

        return answers;
    }
}
