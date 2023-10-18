# Mathematical Expression Evaluator

This program efficiently handles multiple mathematical expressions in bulk and evaluates them using a public Web API. The results are displayed on the console. To ensure parallel request handling, the program utilizes a thread-safe queue implemented with a concurrent linked list. It also incorporates a rate limiter, implementing the token bucket algorithm, to control the number of requests processed.

## Implementation

The program is thoughtfully designed, following key techniques and design patterns:

1. **Adapter Design Pattern:** The `MathOrgAdapter` class acts as an adapter to seamlessly interact with an external Web API for mathematical expression evaluation. It abstracts the API interaction, encapsulating its specific logic.

2. **Concurrency with CompletableFuture:** To efficiently manage multiple concurrent requests for expression evaluation, the code employs `CompletableFuture`. This enables asynchronous task execution, making the program capable of handling a multitude of requests simultaneously.

3. **Rate Limiter (Token Bucket Algorithm):** In order to abide by the API's rate limit of 50 requests per second, a rate limiter is thoughtfully implemented. It ensures that the program never exceeds the allowed rate limit by regulating the rate of sending requests to the API.

4. **Exception Handling:** The program includes robust exception handling to gracefully manage potential errors. For instance, if there's a connection issue with the API, it raises an `ApiConnectException` and provides informative error messages.

5. **Queue for Managing Requests:** Requests are intelligently managed using a queue (`expressionQueue`) before being processed. As long as there are expressions in the queue, the program continually checks whether it can send a request within the rate limit. If not, it gracefully waits for a brief period before retrying.

6. **MVC Architecture and SOLID Principles:** The program adheres to the Model-View-Controller (MVC) architecture, ensuring a clear separation of concerns and modularity. Additionally, it follows SOLID principles to enhance code maintainability and flexibility.

7. **Mocking the In-Memory Database:** For data storage needs, an in-memory database is simulated using a Hashmap. This enables testing and validation without the need for a physical database connection.

## How to Run the Code:

### Prerequisites:
- Java 11 or a compatible version must be installed on your machine.
- This project is built using core Java with no external frameworks.

Importing the Project into Your Java IDE:
Open your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
Choose the option to import an existing project or open a project.
Navigate to the root folder of the cloned repository.
Select the root folder and confirm the import.
Once imported, you should see the project structure in your IDE.
Running the Mock Requests:
To mock the requests and test the program, you can run the Client.java class. This class includes the main program logic and can be executed to evaluate mathematical expressions.

MVC Architecture and Integration:
The codebase is structured following the Model-View-Controller (MVC) architecture. While the core architecture and components are in place, some integration steps may still be pending due to time constraints. However, the foundation for a well-organized and maintainable codebase is already established, and further integration can be completed as needed to meet specific requirements.

With this approach, you can enhance and extend the program while maintaining a structured and modular codebase, which aligns with the principles of good software design.


