package org.Task1;

import org.Task1.EventSimulator;

import java.util.*;



public class Main {
    public static void main(String[] args) {
        // Define the map of outcomes with probabilities
        Map<String, Integer> coinProbabilities = new HashMap<>();
        coinProbabilities.put("Head", 35);
        coinProbabilities.put("Tail", 65);

        // Check if probabilities sum up to 100
        int totalProbability = 0;
        for (int probability : coinProbabilities.values()) {
            totalProbability += probability;
        }

        if (totalProbability != 100) {
            throw new IllegalArgumentException("Probabilities do not add up to 100%. Please check the input.");
        }

        // Create the event simulator
        EventSimulator<String> coinSimulator = new EventSimulator<>();

        // Simulate 1000 coin flips
        Map<String, Integer> results = coinSimulator.generateEvents(coinProbabilities, 1000);

        // Display the results
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + " appeared " + entry.getValue() + " times.");
        }
    }
}
