package projekt.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IBookDAO;
import projekt.book.store.database.sequence.IBookIdSequence;
import projekt.book.store.model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {


    IBookIdSequence bookIdSequence;

    private final List<Book> books = new ArrayList<>();

    public BookDAO(@Autowired IBookIdSequence bookIdSequence) {
        this.books.add(new Book(bookIdSequence.getId(), "Java EE 6. Tworzenie aplikacji w NetBeans 7", "David R. Heffelfinger", 59.00, 10, "978-83-246-8936-1"));
        this.books.add(new Book(bookIdSequence.getId(), "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.36, 10, "978-83-283-9984-6"));
        this.books.add(new Book(bookIdSequence.getId(), "Java. Przewodnik dla początkujących. Wydanie VIII", "Herbert Schildt", 61.38, 10, "978-83-283-9118-5"));
        this.books.add(new Book(bookIdSequence.getId(), "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 60.39, 10, "978-83-283-9896-2"));
        this.bookIdSequence = bookIdSequence;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books); //kopia listy mutowalna
        //List.copyOf(this.books); //kopia listy niemutowalna
    }

    @Override
    public void persistBook(Book book) {
        book.setId(this.bookIdSequence.getId());
        this.books.add(book);
    }

    @Override
    public Optional<Book> getBookById(int id) {
/*       for(Book book : this.books){
           if(book.getId() == id) {
               return Optional.of(book);
           }
       }
        return Optional.empty();*/
        return this.books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public void deleteBook(int id) {
/*        Iterator<Book> iterator = this.books.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }*/
        Optional<Book> bookBox = this.books.stream().filter(b -> b.getId() == id).findFirst();
        if (bookBox.isPresent()) {
            this.books.remove(bookBox.get());
        }
    }

    @Override
    public void updateBook(Book book) {
    /*    Iterator<Book> iterator = this.books.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getId() == book.getId()){
                iterator.remove();
                break;
            }
        }*/
        Optional<Book> bookBox = this.books.stream()
                .filter(b -> b.getId() == book.getId()).findFirst();
        if (bookBox.isPresent()) {
            this.books.remove(bookBox.get());
            this.books.add(book);
        }
    }
}
