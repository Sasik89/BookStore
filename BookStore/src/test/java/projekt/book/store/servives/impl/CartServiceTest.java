package projekt.book.store.servives.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import projekt.book.store.model.Book;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.servives.ICartService;
import projekt.book.store.session.SessionData;

import java.util.Optional;

public class CartServiceTest extends ServiceGenericTest {

    @Autowired
    ICartService cartService;

    @Resource
    SessionData sessionData;

    @Test
    public void addProductToEmptyCartTest(){
        int bookId = 5;
        int expectedBookCount = 1;
        Book expectedBook = generateFakeBook();
        Mockito.when(this.bookDAO.getBookById(5)).thenReturn(Optional.of(generateFakeBook()));
        OrderPosition expectedOrderPosition = new OrderPosition();
        expectedOrderPosition.setBook(expectedBook);
        expectedOrderPosition.setQuantity(1);
        this.cartService.addProductToCart(bookId);

        Assertions.assertEquals(expectedBookCount,
                this.sessionData.getCart().getPositions().size());
        Assertions.assertTrue(this.sessionData.getCart().getPositions().contains(expectedOrderPosition));
    }

    private Book generateFakeBook(){
        return new Book(1, "", "Janusz Kowalski", 50.00,
                10, "979-34-123-1234-8");
    }

}
