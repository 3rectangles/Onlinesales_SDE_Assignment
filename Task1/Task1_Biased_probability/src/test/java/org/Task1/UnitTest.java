package org.Task1;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import org.Task1.EventSimulator;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    @Test
    public void UnitTest() {
        Map<String, Integer> testProbabilities = new HashMap<>();
        testProbabilities.put("A", 10);
        testProbabilities.put("B", 20);
        testProbabilities.put("C", 70);

        EventSimulator<String> testSimulator = new EventSimulator<>();
        int k =500000;
        Map<String, Integer> testResults = testSimulator.generateEvents(testProbabilities, k);

        // Define a percentage-based tolerance for the expected values
        double tolerance = 3.0; // 3% tolerance

        // Calculate the allowable range based on percentages
        double expectedA = 10.0 * k / 100.0;
        double expectedB = 20.0 * k / 100.0;
        double expectedC = 70.0 * k / 100.0;

        // Verify that the actual values are within the percentage-based tolerance
        assertTrue(Math.abs(testResults.get("A") - expectedA) <= expectedA * (tolerance / 100.0));
        assertTrue(Math.abs(testResults.get("B") - expectedB) <= expectedB * (tolerance / 100.0));
        assertTrue(Math.abs(testResults.get("C") - expectedC) <= expectedC * (tolerance / 100.0));
    }

    @Test
    public void testProbabilitiesDoNotAddUp() {
        EventSimulator<String> testSimulator = new EventSimulator<>();
        Map<String, Integer> testProbabilities = new HashMap<>();
        testProbabilities.put("A", 10);
        testProbabilities.put("B", 20);
        testProbabilities.put("C", 30); // Total is 60

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testSimulator.generateEvents(testProbabilities, 1000);
        });

        assertEquals("Probabilities do not add up to 100%. Please check the input.", exception.getMessage());
    }
}