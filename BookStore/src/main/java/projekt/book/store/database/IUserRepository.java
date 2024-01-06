package projekt.book.store.database;

import projekt.book.store.model.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> getByLogin (String login);
    void persistUser(User user);

}
