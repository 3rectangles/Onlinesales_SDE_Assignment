package stratergy;
import java.util.List;

// validates the requests
// can have diff ways to validate the request.
// parses it to json ( not required currently)

public class ReqParser {

    public boolean validate(List<String> expressions) {
        for (String expression : expressions) {
            if (!isValidExpression(expression)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidExpression(String expression) {
        int openParenthesesCount = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                openParenthesesCount++;
            } else if (c == ')') {
                if (openParenthesesCount == 0) {
                    return false; // Unbalanced closing parenthesis
                }
                openParenthesesCount--;
            }
        }
        return openParenthesesCount == 0; // Expression is valid if all parentheses are balanced
    }
}
