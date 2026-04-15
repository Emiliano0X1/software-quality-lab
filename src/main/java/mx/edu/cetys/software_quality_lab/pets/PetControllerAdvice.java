package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.pets.exceptions.InvalidPetDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PetControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    PetController.ApiResponse<Void> handleInvalidPet(InvalidPetDataException exception){
        return new PetController.ApiResponse<>("Invalid Pet Info", null,exception.getMessage());
    }


}
