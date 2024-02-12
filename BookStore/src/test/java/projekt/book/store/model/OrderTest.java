package projekt.book.store.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @Test
    public void getPrettyDateTime() {
        LocalDateTime date = LocalDateTime.of(2020, 10, 10, 15, 40, 0);
        Order order = new Order(123, new User(), Order.Status.NEW, 1230.223, date);

        String expected = "10.10.2020 15:40:00";

        String actual = order.getPrettyTime();

        Assertions.assertEquals(expected, actual);
    }
}