package mx.edu.cetys.software_quality_lab.validators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EmailValidatorService {

    private Set<Character> validChars = new HashSet<>(Arrays.asList('_','.','+','-','#'));
    private Set<Character> validUserChars = new HashSet<>(Arrays.asList('_','-','.','+'));

    public boolean isValid(String email) {
        if(email == null || email.isEmpty()){
            return false;
        }

        //1. Characters valid (1-0 and a-z)
        if(!hasCharactersValid(email)){
            return false;
        };

        //3. Check if the string has # and if yes divide and if not return false
        String[] emailParts = dividedStringsByHashTag(email);
        if(emailParts.length == 0){
            return false;
        }

        String user = emailParts[0];
        String provider_domain = emailParts[1];

        //2 Check if the user has valid characters
        if(!userEmailHasValidChars(user)){
            return false;
        }

        //2.1 Check if the provider+domain has validCahracters
        if(!provider_domainHasValidChars(provider_domain)){
            return false;
        }

        //4. No hay diptongo
        if(userHasDiptongo(user)){
            return false;
        }

        //5. Check domain size
        String[] provider_domain_parts = provider_domain.split("\\.");
        String provider = provider_domain_parts[0];
        String domain = provider_domain_parts[1];

        if(domain.length() < 1 || domain.length() > 5){
            return false;
        }

        //6. Check the size of the email
        if(email.length() > 47){
            return false;
        }

        //7. Check if the email has 4
        if(!email.contains("4")){
            return false;
        }

        return true;
    }

    private boolean hasCharactersValid(String email) {
        for(char c : email.toCharArray()){

            if(Character.isUpperCase(c)){
                return false;
            }
        }
        return true;
    }

    private boolean userEmailHasValidChars(String user) {
        for(char c : user.toCharArray()){
            if(!Character.isLetterOrDigit(c)){
                if(!validUserChars.contains(c)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean provider_domainHasValidChars(String provider_domain) {
        for(char c : provider_domain.toCharArray()) {
            if(!Character.isLetterOrDigit(c)){
                if(c != '.'){
                    return false;
                }
            }
        }
        return true;
    }

    private String[] dividedStringsByHashTag(String email) {

        if(email.contains("#")){
            String[] splitedEmail = email.split("#");
            if(splitedEmail[0].isEmpty()){
                return new String[0];
            }

            return splitedEmail;
        }

        return new String[0];
    }

    private boolean userHasDiptongo(String user) {
        if(user.length() < 2){
            return true;
        }

        if(user.charAt(0) == user.charAt(1)){
            return true;
        }

        return false;
    }


}
