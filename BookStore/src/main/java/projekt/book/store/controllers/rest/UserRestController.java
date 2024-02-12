package projekt.book.store.controllers.rest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.model.User;
import projekt.book.store.model.dto.UserRequest;
import projekt.book.store.servives.IUserService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserRestController {

    @Autowired
    IUserService userService;

    @RequestMapping(path = "/{loginOrId}", method = RequestMethod.GET)
    public ResponseEntity<User> getByLogin(@PathVariable String loginOrId) {
        try {
            int id = Integer.parseInt(loginOrId);
            Optional<User> userBox = this.userService.getById(id);
            if (userBox.isPresent()) {
                return ResponseEntity.status(OK).body(userBox.get());
            }
        } catch (NumberFormatException e) {
            Optional<User> userBox = this.userService.getByLogin(loginOrId);
            if (userBox.isPresent()) {
                return ResponseEntity.status(OK).body(userBox.get());
            }
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody UserRequest user){
        User userModel = new User(0, user.getLogin(), DigestUtils.md5Hex(user.getPassword()),
                user.getName(), user.getSurname(), user.getEmail(), user.getRole());
        user.setId(0); //ustawione zero żeby User który przyjdzie dodał się na bazę
        try{
            this.userService.persistUser(userModel);
        } catch (LoginAlreadyExistException e) {
            return ResponseEntity.status(CONFLICT).build();
        }
        return ResponseEntity.status(CREATED).body(userModel);
    }
}

