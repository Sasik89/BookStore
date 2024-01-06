package projekt.book.store.validators;

import projekt.book.store.exception.BookValidationExpection;
import projekt.book.store.model.Book;

public class BookValidator {

    public static void validateTitle(String title) {
        String regex = "^.+$";
        if (!title.matches(regex)) {
            throw new BookValidationExpection();
        }
    }

    public static void validateAuthor(String author) {
        String regex = "^[A-Z][a-z]+([ A-Z][a-z])? [A-Z][a-z]+([ -][A-Z][a-z]+)?$";
        if (!author.matches(regex)) {
            throw new BookValidationExpection();
        }
    }

    public static void validateISBN(String isbn) {
        String regex = "^(978|979)-[0-9]{2}-[0-9]{2,6}-[0-9]{1,5}-[0-9]$";
        if (!isbn.matches(regex)) {
            throw new BookValidationExpection();
        }
    }


    public static void validateBook(Book book){
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateISBN(book.getIsbn());
    }

}
