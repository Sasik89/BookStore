package projekt.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt.book.store.model.Book;
import projekt.book.store.model.User;

@RestController
@RequestMapping(path = "/api")
public class TestRestController {

    @RequestMapping(path = "/test1", method = RequestMethod.GET)
    public User test1(){
        User user = new User();
        user.setId(5);
        user.setRole(User.Role.ADMIN);
        user.setPassword("asdfgefsdfsfsdsdsdfsdffd");
        user.setSurname("Kowalski");
        user.setName("Janusz");
        user.setEmail("janusz.kowalski@gmail.com");
        user.setLogin("janusz");

        return user;

    }

    @RequestMapping(path = "/test2/{param}", method = RequestMethod.POST)
    public Book test2(@PathVariable String param,
                      @RequestParam int a,
                      @RequestHeader String header1,
                      @RequestHeader String header2,
                      @RequestBody User user){
        System.out.println(param);
        System.out.println(a);
        System.out.println(header1);
        System.out.println(header2);
        System.out.println(user);

        Book book = new Book(13, "Jakiś tytuł", "Jakiś autor", 12, 12, "12312-213-123");
        return book;
    }

    @RequestMapping(path = "/test3", method = RequestMethod.GET)
    public ResponseEntity<Book> test(){
        Book book = new Book(13, "Jakiś tytuł", "Jakiś autor", 12, 12, "12312-213-123");
        ResponseEntity<Book> response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("header1", "wartosc1")
                .header("header2", "wartosc2")
                .body(book);

        return response;
    }

}
