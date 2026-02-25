package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmailValidatorServiceTest {

    @Test
    void shouldReturnFalseWhenEmailIsNull(){
        //Arrange
        EmailValidatorService emailValidate = new EmailValidatorService();

        //Act -> Que es lo que va hacer la logica
        var isValid = emailValidate.isValid(null);

        //Assert
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailIsEmpty(){
        //Arrange
        EmailValidatorService emailValidate = new EmailValidatorService();

        //Act -> Que es lo que va hacer la logica
        var isValid = emailValidate.isValid("");

        //Assert
        assertFalse(isValid);
    }
}
