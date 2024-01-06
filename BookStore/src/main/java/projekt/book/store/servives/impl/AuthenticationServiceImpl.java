package projekt.book.store.servives.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.book.store.database.IUserRepository;
import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;
import projekt.book.store.servives.IAuthenticationService;
import projekt.book.store.session.SessionData;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    IUserRepository userRepository;
    @Resource
    SessionData sessionData;

/*    @Override
    public boolean authenticateWithReturn(String login, String password) {
      *//* User user = this.userRepository.getByLogin(login);
        this.logged = user != null && user.getPassword().equals(DigestUtils.md5Hex(password));
         return this.logged;*//*
        return false;
    }*/

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userRepository.getByLogin(login);
        if(userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))){
            userBox.get().setPassword(null);
            this.sessionData.setUser(userBox.get());
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    @Override
    public void register(User user) throws LoginAlreadyExistException {
        if(this.userRepository.getByLogin(user.getLogin()).isPresent()) {
            throw new LoginAlreadyExistException();
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setRole(User.Role.USER);
        this.userRepository.persistUser(user);
    }
}
