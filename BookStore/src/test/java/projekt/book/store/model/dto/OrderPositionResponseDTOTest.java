package projekt.book.store.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import projekt.book.store.controllers.rest.RestConstans;
import projekt.book.store.model.Book;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;

import static org.junit.jupiter.api.Assertions.*;

class OrderPositionResponseDTOTest {

/*    @Test
    @Disabled
    public void orderPositionToOrderPositionToMappingTest(){
        OrderPosition orderPosition = new OrderPosition(10, new Book(1),
                5, new Order(17));
        //RestConstans.API_LOCATION = "http://localhost:8080/api/v1/";
        String expectedBook = "http://localhost:8080/api/v1/book/1";
        String expectedOrder = "http://localhost:8080/api/v1/order/17";

        OrderPositionResponseDTO actual = new OrderPositionResponseDTO(orderPosition);

        Assertions.assertEquals(orderPosition.getId(), actual.getId());
        Assertions.assertEquals(expectedBook, actual.getBook());
        Assertions.assertEquals(orderPosition.getQuantity(), actual.getQuantity());
        Assertions.assertEquals(expectedOrder, actual.getOrder());
    }*/

}