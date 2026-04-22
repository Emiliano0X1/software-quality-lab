package mx.edu.cetys.software_quality_lab.pets.exceptions;

public class NotFoundPetException extends RuntimeException {
    public NotFoundPetException(String message) {
        super(message);
    }
}
