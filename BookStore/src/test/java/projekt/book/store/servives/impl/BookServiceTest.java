package projekt.book.store.servives.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projekt.book.store.model.Book;


import java.util.ArrayList;
import java.util.List;


@Disabled
class BookServiceTest extends ServiceGenericTest {


    @Test
    public void getAllBooksTest(){
        int expectedListSize = 4;
        List<Integer> expctedBookIds = List.of(1,2,3,4);
        Mockito.when(this.bookDAO.getAllBooks()).thenReturn(generateFakeBook());

        List<Book> actual = this.bookService.getAllBooks();

        Assertions.assertEquals(expectedListSize, actual.size());
        List<Integer> ids = actual.stream().map(Book::getId).toList();
        Assertions.assertTrue(ids.containsAll(expctedBookIds));
        Mockito.verify(this.bookDAO, Mockito.times(1)).getAllBooks();
    }

    private List<Book> generateFakeBook(){
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Java EE 6. Tworzenie aplikacji w NetBeans 7", "David R. Heffelfinger", 59.00, 10, "978-83-246-8936-1"));
        result.add(new Book(2, "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee", 95.36, 10, "978-83-283-9984-6"));
        result.add(new Book(3, "Java. Przewodnik dla początkujących. Wydanie VIII", "Herbert Schildt", 61.38, 10, "978-83-283-9118-5"));
        result.add(new Book(4, "Java. Efektywne programowanie. Wydanie III", "Joshua Bloch", 60.39, 10, "978-83-283-9896-2"));
        return result;
    }

}