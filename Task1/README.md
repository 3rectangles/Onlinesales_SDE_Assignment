# Probability Simulation and Testing

**Core Logic for Biased Event Simulation:**

- The project implements a biased event simulation using the `EventSimulator` class.
- Outcomes are added to a list based on their probabilities, with each outcome repeated according to its probability.
- Simulation selects outcomes randomly from the list, making the event biased to higher-probability outcomes.
- Results are recorded and updated for each simulated event.

**Testing and Tolerance:**

- JUnit library is used for testing the `EventSimulator` class.
- The tests include a tolerance parameter to allow for some deviation in the results
- Simulation Parameter (k):  Set the simulation parameter 'k' to 500,000. This means we simulated 500,000 events based on the provided probabilities.

Tolerance: Applied a tolerance of 3% in our test. This tolerance allowed for a maximum deviation of 3% from the expected outcomes to consider the test as passed.

Test Pass: With these parameters and the 3% tolerance, the test successfully passed, indicating that the biased event simulation produced results roughly matching the expected distribution.
- Test cases check if simulated outcomes roughly match the expected distribution within the specified tolerance.

**How to Run the Code:**

1. **Java 11 and Core Java:**
   - Ensure you have Java 11 or a compatible version installed on your machine.
   - This project is built using core Java with no external frameworks.

2. **Clone the Repository:**
   - Clone this GitHub repository to your local machine using `git clone`.

3. **Import the Project:**
   - Open your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Import the project by selecting the project's root folder.

4. **Maven Build (Optional):**
   - This project uses the Maven build system. If you have Maven installed, you can use it to build and manage dependencies.

5. **Run the Tests:**
   - To run the tests, navigate to the `UnitTest.java` class in your IDE.
   - Run the tests by right-clicking on the class and selecting "Run" or "Run All Tests."

6. **Adjust Tolerance (Optional):**
   - If needed, you can adjust the tolerance in the test cases to accommodate different levels of deviation.

7. **Modify and Use the `EventSimulator` Class:**
   - You can use and modify the `EventSimulator` class to create your biased event simulations in your own Java projects.

