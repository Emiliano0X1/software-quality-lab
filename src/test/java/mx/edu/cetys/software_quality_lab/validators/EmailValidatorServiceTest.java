package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import validators.EmailValidatorService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorServiceTest {
    
    private EmailValidatorService emailValidate;

    @BeforeAll
    static void beforeAll(){
        //Log start time
        
    }
    @BeforeEach
    void beforeEach(){
        emailValidate = new EmailValidatorService();
    }

    @Test
    @DisplayName("Prueba Negativa para cuando Email sea null")
    void shouldReturnFalseWhenEmailIsNull(){
        //Act -> Que es lo que va hacer la logica
        var isValid = emailValidate.isValid(null);
        //Assert
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailIsEmpty(){
        //Act -> Que es lo que va hacer la logica
        var isValid = emailValidate.isValid("");
        //Assert
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenUserSizeIsMinor2(){
        var isValid = emailValidate.isValid("c@#gmil.com4");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailHasInvalidCharacters(){
        var isValid = emailValidate.isValid("#$%lo4#gmil.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailUserHasInvalidCharacters(){
        var isValid = emailValidate.isValid("emiAlino4#gmal.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailProvideAndDomainHasInvalidCharacters(){
        var isValid = emailValidate.isValid("emilino4#gmil/.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailDivisorIsNotHashtag(){
        var isValid = emailValidate.isValid("emilino4@gmil.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenUserDoesNotExist(){
        var isValid = emailValidate.isValid("#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailStartsWithDiptongo(){
        var isValid = emailValidate.isValid("eemiliano4#gmail.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailDomainIsNotProperLength(){
        var isValid = emailValidate.isValid("emilano4#gmail.commer");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailLengthIsBiggerThan47(){
        var isValid = emailValidate.isValid("emilinojdsibfusndjfsdfojsdnfsdofjsifjsdifjsohdfosdhifihsdfo4#gmil.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalse_WhenEmailDoesNotContain4(){
        var isValid = emailValidate.isValid("emiliano#gmil.com");
        assertFalse(isValid);
    }

    @Test
    void shouldReturnTrue_WhenEmailIsValid(){
        var isValid = emailValidate.isValid("emil-_i+ano4#gmal.com");
        assertTrue(isValid);
    }

    @DisplayName("Validate Bad Emails")
    @ParameterizedTest
    @ValueSource(strings = {
            "#gmail.com",
            "emilano4#gmail.commer",
            "emilino4#gmil/.com",
    })
    void invalidEmails(String email){
        var isValid = emailValidate.isValid(email);
        assertFalse(isValid);
    }
}
