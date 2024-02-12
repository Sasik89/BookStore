package projekt.book.store.servives.impl;


import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;
import projekt.book.store.servives.IAuthenticationService;
import projekt.book.store.session.SessionData;

import java.util.Optional;


@WebAppConfiguration //włacza mechanizm sesji
class AuthenticationServiceTest extends ServiceGenericTest{

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionData sessionData;

    @Test
    public void correctAuthenticationTest(){
        String login = "janusz";
        String password = "janusz123";
        User user = generateFakeUser(DigestUtils.md5Hex(password));
        Mockito.when(this.userRepository.getByLogin("janusz")).thenReturn(Optional.of(user));

    this.authenticationService.authenticate(login, password);

        Assertions.assertNotNull(this.sessionData.getUser());
        Assertions.assertNull(this.sessionData.getUser().getPassword());
        Assertions.assertSame(user, this.sessionData.getUser());
        Mockito.verify(this.userRepository, Mockito.times(1))
                .getByLogin(Mockito.any());
    }

    @Test
    public void incorrectPasswordAuthenticationTest(){
        String login = "janusz";
        String password = "zlehaslo";
        User user = generateFakeUser(DigestUtils.md5Hex("dobrehaslo"));
        Mockito.when(this.userRepository.getByLogin("janusz")).thenReturn(Optional.of(user));

        this.authenticationService.authenticate(login, password);

        Assertions.assertNull(this.sessionData.getUser());
        Mockito.verify(this.userRepository, Mockito.times(1))
                .getByLogin(Mockito.any());
    }

    @Test
    public void registerUserTest() throws LoginAlreadyExistException {
        User user = generateFakeUser(DigestUtils.md5Hex("janusz123"));
        String expectedPassword = DigestUtils.md5Hex("janusz123");

        this.authenticationService.register(user);

        Mockito.verify(this.userRepository, Mockito.times(1))
                .persistUser(Mockito.any());
        Assertions.assertEquals(expectedPassword, user.getPassword());
        Assertions.assertEquals(User.Role.USER, user.getRole());
    }

    @Test
    public void loginAlreadyTest() throws LoginAlreadyExistException {
        User user = generateFakeUser("janusz123");
        Mockito.doThrow(LoginAlreadyExistException.class)
                .when(this.userRepository).persistUser(Mockito.any());

        Assertions.assertThrows(LoginAlreadyExistException.class,
                ()->this.authenticationService.register(user));
    }

    private User generateFakeUser(String password){
      return new User(10, "janusz", password,
                "Janusz", "Kowalski", "janusz123@gmail.com", User.Role.USER);
    }


}