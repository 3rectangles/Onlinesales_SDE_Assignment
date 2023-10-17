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

import java.util.ArrayList;
import java.util.List;

public class EvaluateRequestService {

    public Request createRequest(List<String > listExpression) {
        Request req = new Request();
        // loop through the expressions in string
        // create expression using ExpressionRepository
        // create Request Object
        // push the Expression Objects in the Request using RequestRepository
        // Return Request object


        return  req;

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
