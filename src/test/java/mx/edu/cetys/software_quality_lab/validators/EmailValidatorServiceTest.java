package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldReturnFalse_WhenEmailHasInvalidCharacters(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("#$%lo4#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailUserHasInvalidCharacters(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emiAliano4#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailProvideAndDomainHasInvalidCharacters(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emiliano4#gmail/.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailDivisorIsNotHashtag(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emiliano4@gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailStartsWithDiptongo(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("eemiliano4#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailDomainIsNotProperLength(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emiliano4#gmail.commer");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailLengthIsBiggerThan47(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emilianojdsibfusndjfsdfojsdnfsdofjsifjsdifjsohdfosdhifiohsdfo4#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailDoesNotContain4(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emiliano#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnTrue_WhenEmailIsValid(){
        EmailValidatorService emailValidate = new EmailValidatorService();
        var isValid = emailValidate.isValid("emil-_iano4#gmail.com");
        assertTrue(isValid);
    }


}
