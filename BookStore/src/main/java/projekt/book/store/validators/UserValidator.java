package projekt.book.store.validators;

import projekt.book.store.exception.UserValidationException;
import projekt.book.store.model.User;

public class UserValidator {

    /*public static void validateName(String name) throws UserValidationException {
        if (!(name.length() < 2)) {
            throw new UserValidationException();
        }
        for(Character c : name.toCharArray()) {
            if(!Character.isLetter(c)){
                throw new UserValidationException();
            }
        }
        if((name.charAt(0) + "").equals((name.charAt(0) + "").toLowerCase())) {
            throw new UserValidationException();
        }
    }*/

    public static void validateName(String name) {
        String regex = "^[A-Z][a-z]+$";
        if (!name.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validateSurname(String surname) {
        String regex = "^[A-Z][a-z]+([ -][A-Z][a-z]+)?$";
        if (!surname.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validateLogin(String login) {
        String regax = "^.{5,}$";
        if (!login.matches(regax)) {
            throw new UserValidationException();
        }
    }

    public static void validatePassword(String password) {
        String regax = "^.{5,}$";
        if (!password.matches(regax)) {
            throw new UserValidationException();
        }
    }


    public static void validateEmail(String email) {
        String regax = "^[\\w\\.-]+@[\\w\\.-]+\\.[A-Za-z0-9]{2,3}$";
        if (!email.matches(regax)) {
            throw new UserValidationException();
        }
    }

    public static void validatePasswordEquality(String pass1, String pass2){
        if(!pass1.equals(pass2)){
            throw new UserValidationException();
        }
    }

    public static void validateUser(User user){
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateEmail(user.getEmail());
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
    }


}
