package mx.edu.cetys.software_quality_lab.commons;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
