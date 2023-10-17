package exception;

public class ApiConnectException extends Exception {

    public ApiConnectException(String msg){
            super(msg);
        }

    public ApiConnectException(ApiConnectException e) {
        super(e.getMessage());
    }
}

