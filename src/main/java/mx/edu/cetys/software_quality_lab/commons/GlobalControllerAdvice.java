package mx.edu.cetys.software_quality_lab.commons;

import mx.edu.cetys.software_quality_lab.pets.exceptions.InvalidPetDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {


    /*
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<Void> handleInvalidPet(Exception exception){


        return new ApiResponse<>("Invalid Pet Info", null,exception.getMessage());
    }

     */

}
