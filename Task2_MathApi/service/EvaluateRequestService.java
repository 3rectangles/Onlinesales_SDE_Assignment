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


import com.sun.source.tree.NewArrayTree;
import models.Expression;
import models.Request;
import repositories.RequestRepository;

import java.util.ArrayList;
import java.util.List;

public class EvaluateRequestService {
    private  RequestRepository requestRepository;
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

    public List<String> evaluateExpression(List<String> expressions) {

        List<String> answers = new ArrayList<>();
        // call the 3rd party API, store the future,
        // make a blocking call so that all expression of a request gets completed
        // before returning the response


        // check the result of the future, if response code API is valid
        // put the ans
        // else put the response code and error


        return answers;
    }
}
