package dtos;

import java.util.List;

public class SolveRequestDto {
    // list of string
    public List<String> expressions;

    public List<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }
}
