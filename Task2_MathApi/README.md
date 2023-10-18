# Mathematical Expression Evaluator

This program accepts multiple mathematical expressions in bulk and evaluates each of them using a public Web API. The program displays the result of each expression on the console. It assumes that the API supports 50 requests per second per client, while the application is expected to evaluate at least 500 expressions per second. To handle concurrent requests, the program uses a rate limiter.

## Implementation

The program is implemented using the following techniques and design patterns:

1. **Adapter Design Pattern:** The `MathOrgAdapter` class acts as an adapter to interact with the external Web API for evaluating mathematical expressions. It abstracts the API interaction, encapsulating the API's specific logic.

2. **Concurrency with CompletableFuture:** To efficiently handle multiple concurrent requests for expression evaluation, `CompletableFuture` is used. It allows asynchronous execution of tasks, making the program capable of handling many requests simultaneously.

3. **Rate Limiter (Token Bucket Algorithm):** To adhere to the API's rate limit of 50 requests per second, a rate limiter is implemented. It ensures that the program doesn't exceed the allowed rate limit by controlling the rate of sending requests to the API.

4. **Exception Handling:** The program utilizes exception handling to manage potential errors. For example, if there's an issue with connecting to the API, it raises an `ApiConnectException` and provides error messages.

5. **Queue for Managing Requests:** Requests are stored in a queue (`expressionQueue`) before being processed. While there are expressions in the queue, the program continuously checks if it can send a request according to the rate limit. If not, it waits for a short time before trying again.

## Usage

The program accepts mathematical expressions as input, with one expression per line. It evaluates each expression using the Web API, and the results are displayed on the console. To indicate the end of input, you can use "end."

Here's an example of the input and expected output:

**Input:**
