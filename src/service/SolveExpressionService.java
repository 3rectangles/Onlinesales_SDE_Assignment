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


public class SolveExpressionService {
}
