package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.commons.ApiResponse;
import mx.edu.cetys.software_quality_lab.pets.exceptions.InvalidPetDataException;
import mx.edu.cetys.software_quality_lab.pets.exceptions.NotFoundPetException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PetControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<Void> handleInvalidPet(InvalidPetDataException exception){
        return new ApiResponse<>("Invalid Pet Info", null,exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiResponse<Void> handleNotFoundPet(NotFoundPetException e){
        return new ApiResponse<>("Pet not found", null,e.getMessage());
    }


}
