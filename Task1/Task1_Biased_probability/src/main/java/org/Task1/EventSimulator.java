package org.Task1;

import java.util.*;

class EventSimulator<T> {
    public Map<T, Integer> generateEvents(Map<T, Integer> eventProbabilities, int k) {

        // Check if probabilities add up to 100%
        int totalProbability = 0;
        for (Integer probability : eventProbabilities.values()) {
            totalProbability += probability;
        }

        if (totalProbability != 100) {
            throw new IllegalArgumentException("Probabilities do not add up to 100%. Please check the input.");
        }


        // Create a list to store outcomes based on their probabilities
        List<T> outcomeList = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : eventProbabilities.entrySet()) {
            T outcome = entry.getKey();
            int probability = entry.getValue();
            // Add the outcome to the list 'probability' times
            for (int i = 0; i < probability; i++) {
                outcomeList.add(outcome);
            }
        }

        // Create a map to store outcomes and their frequencies
        Map<T, Integer> result = new HashMap<>();
        Random random = new Random();

        // Simulate 'k' events
        for (int i = 0; i < k; i++) {
            // Generate a random index to select an outcome from the list
            int randIndex = random.nextInt(outcomeList.size());
            T outcome = outcomeList.get(randIndex);
            // Update the outcome's frequency in the result map
            result.put(outcome, result.getOrDefault(outcome, 0) + 1);
        }

        return result;
    }
}
