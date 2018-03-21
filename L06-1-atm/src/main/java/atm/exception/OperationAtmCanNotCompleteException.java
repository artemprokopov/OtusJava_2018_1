package atm.exception;

public class OperationAtmCanNotCompleteException extends RuntimeException {
    public OperationAtmCanNotCompleteException(String message) {
        super(message);
    }
}
