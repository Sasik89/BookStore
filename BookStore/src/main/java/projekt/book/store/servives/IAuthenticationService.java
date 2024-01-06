package projekt.book.store.servives;

import jakarta.servlet.http.HttpServletRequest;
import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;

public interface IAuthenticationService {

    void authenticate (String login, String password);
    void logout(HttpServletRequest request);
    void register(User user) throws LoginAlreadyExistException;
}
