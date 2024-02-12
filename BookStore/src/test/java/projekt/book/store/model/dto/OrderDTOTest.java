package projekt.book.store.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import projekt.book.store.controllers.rest.RestConstans;
import projekt.book.store.model.Book;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.model.User;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static projekt.book.store.controllers.rest.RestConstans.*;

class OrderDTOTest {

    @Test
    public void orderToOrderDTOMapingTest(){
        LocalDateTime date = LocalDateTime.of(2020, 10, 10,
                15, 40, 0);
        Order order = new Order(15, new User(15), Order.Status.NEW,
                1230.223, date);
        order.getOrderPosition().add(new OrderPosition(1,new Book(), 234));
        order.getOrderPosition().add(new OrderPosition(2,new Book(), 234));
        order.getOrderPosition().add(new OrderPosition(3,new Book(), 234));

        String expectedUser = "http://localhost:8080/api/v1/user/15";
        Set<String> expectedOrderPosition = Set.of(
                "http://localhost:8080/api/v1/order-position/1",
                "http://localhost:8080/api/v1/order-position/2",
                "http://localhost:8080/api/v1/order-position/3"
        );

        OrderDTO actual = new OrderDTO(order);

        Assertions.assertEquals(order.getId(), actual.getId());
        Assertions.assertEquals(expectedUser, actual.getUser());
        Assertions.assertEquals(expectedOrderPosition, actual.getOrderPosition());
        Assertions.assertEquals(Order.Status.NEW, actual.getStatus());
        Assertions.assertEquals(order.getTotal(), actual.getTotal());
        Assertions.assertEquals(date, actual.getDateTime());
    }

}