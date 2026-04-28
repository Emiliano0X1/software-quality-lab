package mx.edu.cetys.software_quality_lab.commons;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
