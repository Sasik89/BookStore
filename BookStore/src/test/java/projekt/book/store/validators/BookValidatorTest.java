package projekt.book.store.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projekt.book.store.exception.BookValidationExpection;
import projekt.book.store.model.Book;

import static org.junit.jupiter.api.Assertions.*;

class BookValidatorTest {

    @Test
    public void correctTitleValidatorTest(){
        String title = "title";

        BookValidator.validateTitle(title);
    }

    @Test
    public void incorrectTitleValidatorTest(){
        String title = "";

        Assertions.assertThrows(BookValidationExpection.class,
                () -> BookValidator.validateTitle(title));
    }

    @Test
    public void correctAuthorWithOneNameAndOneSurnameValidatorTest(){
        String author = "Janusz Kowalski";

        BookValidator.validateAuthor(author);
    }
    @Test
    public void incorrectAuthorWithWrongNameAndOneSurnameValidatorTest(){
        String author = "janusz Kowalski";

        Assertions.assertThrows(BookValidationExpection.class,()->BookValidator.validateAuthor(author));
    }
    @Test
    public void incorrectAuthorWithoutSpaceBetweenNameAndSurnameValidatorTest(){
        String author = "JanuszKowalski";

        Assertions.assertThrows(BookValidationExpection.class,()->BookValidator.validateAuthor(author));
    }

    @Test
    public void correctISBNValidatorTest(){
        String isbn = "979-34-123-1234-9";

        BookValidator.validateISBN(isbn);
    }

    @Test
    public void incorrectISBNValidatorTest(){
        String isbn = "777-34-123-1234-0";

        Assertions.assertThrows(BookValidationExpection.class,
                () -> BookValidator.validateISBN(isbn));
    }

    @Test
    public void correctBookValidatorTest(){
        Book book = new Book(1, "Tytuł", "Janusz Kowalski", 50.00,
                10, "979-34-123-1234-8");

        BookValidator.validateBook(book);
    }

    public void bookWithIncorrectTitleValidatorTest(){
        Book book = new Book(1, "", "Janusz Kowalski", 50.00,
                10, "979-34-123-1234-8");

        Assertions.assertThrows(BookValidationExpection.class,
                ()->BookValidator.validateBook(book));
    }
}