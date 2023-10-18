package repositories;

import models.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestRepository {
    private final Map<Integer, Request> requestStorage;
    private int nextId;

    public RequestRepository() {
        this.requestStorage = new HashMap<>();
        this.nextId = 1; // Initialize the ID generator
    }

    public Request save(Request request) {
        // Assign a unique ID to the request
        int id = nextId++;
        request.setId(id);

        // Store the request in the repository
        requestStorage.put(id, request);

        return request;
    }

    public Request getRequestById(int id) {
        return requestStorage.get(id);
    }

    public void updateRequest(Request request) {
        // Check if the request already exists
        int id = request.getId();
        if (requestStorage.containsKey(id)) {
            requestStorage.put(id, request);
        }
    }

    public void deleteRequest(int id) {
        requestStorage.remove(id);
    }
}
