package projekt.book.store.servives.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.book.store.database.IBookDAO;
import projekt.book.store.model.Book;
import projekt.book.store.servives.IBookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    @Override
    public List<Book> getFilteredBook(String pattern) {
        return this.bookDAO.getByPattern(pattern);
/*        List<Book> books = getAllBooks();
        List<Book> filtered = new ArrayList<>();
        for(Book book : books) {
            if(book.getTitle()
                    .toLowerCase()
                    .contains(pattern.toLowerCase()) ||
                    book.getAuthor()
                            .toLowerCase()
                            .contains(pattern.toLowerCase())) {
                filtered.add(book);
            }
        }
        return filtered;
      *//* return books.stream().filter(b -> b.getTitle().toLowerCase().contains(pattern.toLowerCase())
            || b.getAuthor().toLowerCase().contains(pattern.toLowerCase())).toList();
*/
    }

    @Override
    public Optional<Book> persistBook(Book book) {
        this.bookDAO.persistBook(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        return this.bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        this.bookDAO.deleteBook(id);
    }
}
