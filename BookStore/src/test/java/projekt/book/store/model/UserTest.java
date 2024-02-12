package projekt.book.store.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void copyUserTest(){
        User user = new User(123,"admin","janusz123", "karol",
                "Krawczyk", "karolkrawczyk@gmail.com" , User.Role.USER);

    User copiedUser = User.copyOf(user);

        Assertions.assertNotSame(user,copiedUser); //metoda sprawdza czy oba obkiety posiadają rózne referencje
        Assertions.assertEquals(user.getId(), copiedUser.getId());
        Assertions.assertEquals(user.getName(), copiedUser.getName());
        Assertions.assertEquals(user.getSurname(), copiedUser.getSurname());
        Assertions.assertEquals(user.getPassword(), copiedUser.getPassword());
        Assertions.assertEquals(user.getRole(), copiedUser.getRole());
        Assertions.assertEquals(user.getLogin(), copiedUser.getLogin());
        Assertions.assertEquals(user.getEmail(), copiedUser.getEmail());
    }



}