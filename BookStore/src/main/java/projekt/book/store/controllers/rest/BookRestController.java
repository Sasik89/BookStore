package projekt.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt.book.store.model.Book;
import projekt.book.store.model.dto.BookListResponse;
import projekt.book.store.model.dto.ListResponse;
import projekt.book.store.servives.IBookService;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/book")
public class BookRestController {

    @Autowired
    IBookService bookService;


    //endpoint który wyjmuje książki i potrafi filtrować
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ListResponse getBook(@RequestParam(required = false) String pattern) {
        if (pattern == null) {
            return new ListResponse<>(this.bookService.getAllBooks());
        }
        return new ListResponse<>(this.bookService.getFilteredBook(pattern));
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        book.setId(0); //ustawiam zero żeby książka mogła się zapisać
        Optional<Book> bookFromDB = this.bookService.persistBook(book);
        if (bookFromDB.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookFromDB.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Optional<Book> bookBox = this.bookService.getBookById(id);
        return bookBox.map(ResponseEntity::ok)
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
        book.setId(id);
        if(this.bookService.getBookById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Optional<Book> bookFromDB = this.bookService.updateBook(book);
            return bookFromDB.map(ResponseEntity::ok)
                    .orElseGet(()-> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id){
        this.bookService.deleteBook(id);
    }

}
