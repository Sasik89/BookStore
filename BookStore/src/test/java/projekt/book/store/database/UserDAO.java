package projekt.book.store.database;

import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;
import projekt.book.store.servives.IUserService;

import java.util.Optional;

public class UserDAO implements IUserRepository{


    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void persistUser(User user) throws LoginAlreadyExistException {

    }
}
