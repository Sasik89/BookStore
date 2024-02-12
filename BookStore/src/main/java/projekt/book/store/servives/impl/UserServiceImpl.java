package projekt.book.store.servives.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.book.store.database.IUserRepository;
import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;
import projekt.book.store.servives.IUserService;

import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> getByLogin(String login) {
        return this.userRepository.getByLogin(login);
    }

    @Override
    public Optional<User> getById(int id) {
        return this.userRepository.getById(id);
    }

    @Override
    public void persistUser(User user) throws LoginAlreadyExistException {
        this.userRepository.persistUser(user);
    }
}
