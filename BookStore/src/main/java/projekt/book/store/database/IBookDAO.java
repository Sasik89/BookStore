package projekt.book.store.database;

import projekt.book.store.model.Book;

import java.util.List;
import java.util.Optional;


public interface IBookDAO {

    List<Book> getAllBooks();
    List<Book> getByPattern(String pattern);
    Optional<Book> persistBook(Book book);
    Optional<Book> getBookById(int id);
    void deleteBook(int id);

    Optional<Book> updateBook(Book book);



}
