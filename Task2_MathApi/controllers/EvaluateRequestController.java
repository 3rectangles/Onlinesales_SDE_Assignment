package controllers;

import dtos.SolveRequestDto;
import dtos.SolveResponseDto;
import models.EvaluateRequestStatus;
import models.Request;
import service.EvaluateRequestService;
import stratergy.RateLimiter;
import stratergy.ReqParser;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class EvaluateRequestController {
    private RateLimiter rateLimiter;
    private Queue<SolveRequestDto> reqQueue;
    private EvaluateRequestService evaluateRequestService;
    private ReqParser reqParser;

    public EvaluateRequestController(RateLimiter limiter,
                                     EvaluateRequestService evaluateRequestService,
                                     ReqParser reqParser)
    {
        this.rateLimiter = limiter;
        this.reqQueue = new ConcurrentLinkedDeque<>();
        this.evaluateRequestService =evaluateRequestService;
        this.reqParser = reqParser;
    }

    public SolveResponseDto solve( SolveRequestDto req ) throws IllegalArgumentException{
        // add the incoming req to the queue, for concurrent users
        reqQueue.add(req);
        // limit the rate of requests handled by the application

        while (!reqQueue.isEmpty()) {
            if (rateLimiter.isAllowed()) { // Check if it's allowed to process acc to rate limitng

                // validate the solvedRequestDto
                if(!reqParser.validate(req.expressions))
                {
                   return new SolveResponseDto(null,null, EvaluateRequestStatus.FAILURE);
                }



                // make request object, if validation passed
                 Request request = evaluateRequestService.createRequest(req.expressions);
                // Solve the expression in the request object
                 List<String> answers =  evaluateRequestService.evaluateExpression(req.expressions);
                 // return ResponseDto
                 SolveResponseDto responseDto = new SolveResponseDto(request,
                         answers,EvaluateRequestStatus.SUCCESS);
            } else {
                try {
                    // Sleep for a short time before checking again
                    TimeUnit.MILLISECONDS.sleep(100); // Adjust the sleep time as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return new SolveResponseDto(null,null, EvaluateRequestStatus.FAILURE);

    }

}
