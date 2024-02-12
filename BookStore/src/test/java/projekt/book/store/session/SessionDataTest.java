package projekt.book.store.session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projekt.book.store.model.User;

import static org.junit.jupiter.api.Assertions.*;

class SessionDataTest {


    @Test
    public void isUserNotLoggedTest(){
        SessionData sessionData = new SessionData();
        boolean actual = sessionData.isLogged();
        Assertions.assertFalse(actual);
    }

    @Test
    public void isUserNullByDefaultTest(){
        SessionData sessionData = new SessionData();
        User actual = sessionData.getUser();
        Assertions.assertNull(actual);
    }

    @Test
    public void isUserLoggedTest(){
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User());
        boolean actual = sessionData.isLogged();
        Assertions.assertTrue(actual);
    }


    @Test
    public void isNotLoggedUserAdminIfNull(){
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);
        Assertions.assertFalse(sessionData.isAdmin());

    }

    @Test
    public void isLoggedUserAdminTest(){
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(123,"admin","21232f297a57a5a743894a0e4a801fc3", "Karol",
                "Krawczyk", "karolkrawczyk@gmail.com" , User.Role.ADMIN));
        Assertions.assertTrue(sessionData.isAdmin());
    }

    @Test
    public void isLoggedUserNotAdminTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(123,"admin","21232f297a57a5a743894a0e4a801fc3", "Karol",
                "Krawczyk", "karolkrawczyk@gmail.com" , User.Role.USER));
        Assertions.assertFalse(sessionData.isAdmin());
    }

    @Test
    public void GetIngoIfNotNullTest(){
        SessionData sessionData = new SessionData();
        sessionData.setInfo("info");
        String expected = "info";
        String excepted2 = "";

        String actual = sessionData.getInfo();
        String actual2 = sessionData.getInfo();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(excepted2, actual2);
    }

    @Test
    public void getInfoNullTest(){
        SessionData sessionData = new SessionData();
        sessionData.setInfo(null);
        String expected = "";
        String excepted2 = "";

        String actual = sessionData.getInfo();
        String actual2 = sessionData.getInfo();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(excepted2, actual2);
    }

}