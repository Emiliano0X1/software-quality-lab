package mx.edu.cetys.software_quality_lab.validators;

public class EmailValidatorService {

    public boolean isValid(String email) {
        if(email == null || email.isEmpty()){
            return false;
        }

        return true;
    }
}
