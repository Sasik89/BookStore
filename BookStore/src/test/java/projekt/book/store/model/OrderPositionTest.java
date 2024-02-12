package projekt.book.store.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderPositionTest {



    @Test
    public void incrementOrderPositionQuantityTest()
    {
        OrderPosition orderPosition = new OrderPosition(1234,new Book(), 7);
        int expectedQuantity = 8;

        orderPosition.incrementQuantity();

        Assertions.assertEquals(expectedQuantity, orderPosition.getQuantity());
    }
}