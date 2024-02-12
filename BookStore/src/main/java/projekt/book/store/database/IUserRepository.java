package projekt.book.store.database;

import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> getByLogin (String login);
    Optional<User> getById(int id);
    void persistUser(User user) throws LoginAlreadyExistException;

}
