package models;

import java.util.List;

public class Request {
    private int id;
    private List<String> expressions;
    private List<String> results;

    public Request() {
        // You can initialize fields or generate an ID here
        this.id = 0; // Initialize with a unique ID
        this.expressions = null;
        this.results = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
