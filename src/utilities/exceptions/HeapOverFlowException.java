package utilities.exceptions;

public class HeapOverFlowException extends OverFlowException {
    @Override
    public String getMessage() {
        return "Heap overflow";
    }
}
