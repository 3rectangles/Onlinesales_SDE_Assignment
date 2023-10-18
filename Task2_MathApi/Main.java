import dtos.SolveRequestDto;
import controllers.EvaluateRequestController;
import dtos.SolveResponseDto;
import exception.ApiConnectException;
import models.EvaluateRequestStatus;
import repositories.RequestRepository;
import service.EvaluateRequestService;
import stratergy.RateLimiter;
import stratergy.RateLimiterFactory;
import stratergy.ReqParser;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a SolveRequestDto with your list of expressions
        SolveRequestDto requestDto = new SolveRequestDto();
        requestDto.setExpressions(Arrays.asList(
                "2 * (7 - 3)",
                "2 / 3",
                "sqrt(16)",
                "sin(45 deg)",
                "log(100)",
                "2^5",
                "1 + 2 + 3",
                "3! / 2"
                // Add more expressions here
        ));

        // initialise the objects that will passed
        // Create a RateLimiter using the factory
        RateLimiter rateLimiter = null;
        try {
            rateLimiter = RateLimiterFactory.createRateLimitClass("token_bucket", 100);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Create an instance of RequestRepository
        RequestRepository requestRepository = new RequestRepository();

        // Create an instance of EvaluateRequestService and pass the RequestRepository
        EvaluateRequestService evaluateRequestService = new EvaluateRequestService(requestRepository);


        // Create an instance of ReqParser
        ReqParser reqParser = new ReqParser();

// Create controller object and pass the instances of the services
        EvaluateRequestController controller = new EvaluateRequestController(
                rateLimiter,
                evaluateRequestService,
                reqParser
        );

        try {
            // Pass the request to the controller
            SolveResponseDto responseDto = controller.solve(requestDto);

            if (responseDto.getResponseStatus() == EvaluateRequestStatus.SUCCESS) {
                printResponse(requestDto,responseDto);
            } else {
                System.out.println("Request processing failed.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ApiConnectException e) {
            System.out.println(e.getMessage());
        }


    }

    // Function to print the response with the desired format
    private static void printResponse(SolveRequestDto requestDto, SolveResponseDto responseDto) {
        List<String> expressions = requestDto.getExpressions();
        List<String> answers = responseDto.getAnswers();

        if (responseDto.getResponseStatus() == EvaluateRequestStatus.SUCCESS) {
            for (int i = 0; i < expressions.size(); i++) {
                String expression = expressions.get(i);
                String answer = answers.get(i);
                System.out.println(expression + " => " + answer);
            }
        } else {
            System.out.println("Request processing failed.");
        }
    }

}
