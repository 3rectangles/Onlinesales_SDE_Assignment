package service;

public class ExpressionEvaluationServiceFactory {
    public static ExpressionEvaluationService createApiAdapter(String choice) throws IllegalArgumentException {
        if (choice.equals("math.js")) {
            // Create the ConsoleLoggerAdapter to adapt the ConsoleLogger
            return new MathOrgAdapter();
        } else {
            throw new IllegalArgumentException("Invalid logger type");
        }
    }

}
