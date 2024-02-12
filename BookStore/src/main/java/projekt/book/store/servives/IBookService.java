package projekt.book.store.servives;

import projekt.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    List<Book> getAllBooks();
    List<Book> getFilteredBook(String pattern);

    Optional<Book> persistBook(Book book);

    Optional<Book> getBookById(int id);
    Optional<Book> updateBook(Book book);
    void deleteBook(int id);


}
