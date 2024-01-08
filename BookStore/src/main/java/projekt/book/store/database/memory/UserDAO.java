package projekt.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IUserRepository;
import projekt.book.store.database.sequence.IUserIdSequence;
import projekt.book.store.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements IUserRepository {

    private final List<User> users = new ArrayList<>();

    IUserIdSequence userIdSequence;

    public UserDAO(@Autowired IUserIdSequence userIdSequence){
      this.users.add(new User(userIdSequence.getId(), "admin","21232f297a57a5a743894a0e4a801fc3", "Karol",
              "Krawczyk", "karolkrawczyk@gmail.com" , User.Role.ADMIN));
      this.users.add(new User(userIdSequence.getId(), "janusz","087d9c5e13bdd64a82bef8e013625c32", "Janusz",
                "Kowalski", "januszkowalski@gmail.com", User.Role.USER));
        this.userIdSequence = userIdSequence;
    }

    @Override
    public Optional<User> getByLogin(String login) {
/*        for(User user : this.users) {
            if(user.getLogin().equals(login)){
                return Optional.of(User.copyOf(user));
            }
        }
        return Optional.empty();*/
       return this.users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
    }

    @Override
    public void persistUser(User user) {
        user.setId(this.userIdSequence.getId());
        this.users.add(user);
    }


}
