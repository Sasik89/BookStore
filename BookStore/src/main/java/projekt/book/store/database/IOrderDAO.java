package projekt.book.store.database;

import projekt.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {


    void persistOrder(Order order);

    List<Order> getOrdersByUserId(int userId);

    Optional<Order> getOrderById(int id);


}
