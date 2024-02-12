package projekt.book.store.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projekt.book.store.exception.UserValidationException;
import projekt.book.store.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    public void notUpperCaseFirstLetterNameValidatorTest(){
        String name = "janusz";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateName(name));
    }

    @Test
    public void correctNameValidatorTest(){
        String name = "Janusz";

        UserValidator.validateName(name);
    }

    @Test
    public void correctSurnameValidatorTest(){
        String surname = "Kowalski";

        UserValidator.validateSurname(surname);
    }

    @Test
    public void surnameWithNumberValidatorTest(){
        String surname = "Kowalski123";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateSurname(surname));
    }

    @Test
    public void correctLoginTest(){
        String login = "janusz123";

        UserValidator.validateLogin(login);
    }

    @Test
    public void tooSchortLoginTest(){
        String login = "abc";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateLogin(login));

    }
    @Test
    public void correctPasswordTest(){
        String pass = "janusz123";

        UserValidator.validatePassword(pass);

    }
    @Test
    public void tooShortPasswordTest(){
        String pass = "abc";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validatePassword(pass));
    }

    @Test
    public void correctEmailTest(){
        String email = "basteksas@poczta.fm";

        UserValidator.validateEmail(email);
    }

    @Test
    public void emailWithoutAtTest(){
        String email = "basteksas.poczta.fm";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateEmail(email));
    }


    @Test
    public void passwordsEqualTest(){
        String pass1 = "janusz123";
        String pass2 = "janusz123";

        UserValidator.validatePasswordEquality(pass1, pass2);
    }
    @Test
    public void passwordsNotEqualTest(){
        String pass1 = "janusz1234";
        String pass2 = "janusz123";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validatePasswordEquality(pass1, pass2));

    }

    @Test
    public void correctUserTest(){
        User user = new User(123,"admin","janusz123", "Karol",
                "Krawczyk", "karolkrawczyk@gmail.com" , User.Role.USER);

        UserValidator.validateUser(user);
    }

    @Test
    public void IncorrectUserTest(){
        User user = new User(123,"admin","janusz123", "karol",
                "Krawczyk", "karolkrawczyk@gmail.com" , User.Role.USER);

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateUser(user));
    }


}