package projekt.book.store.database;

import projekt.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO implements IBookDAO{
    @Override
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Java EE 6. Tworzenie aplikacji w NetBeans 7", "David R. Heffelfinger", 59.00, 10, "978-83-246-8936-1"));
        result.add(new Book(2, "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.36, 10, "978-83-283-9984-6"));
        result.add(new Book(3, "Java. Przewodnik dla początkujących. Wydanie VIII", "Herbert Schildt", 61.38, 10, "978-83-283-9118-5"));
        result.add(new Book(4, "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 60.39, 10, "978-83-283-9896-2"));

        return result;
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return null;
    }

    @Override
    public Optional<Book> persistBook(Book book) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.empty();
    }

    @Override
    public void deleteBook(int id) {

    }

    @Override
    public Optional<Book> updateBook(Book book) {
        return Optional.empty();
    }
}
