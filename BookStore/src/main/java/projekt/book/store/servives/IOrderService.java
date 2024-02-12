package projekt.book.store.servives;

import projekt.book.store.model.Order;
import projekt.book.store.model.dto.SaveOrderRequest;

import java.util.List;
import java.util.Optional;

public interface IOrderService {


    List<Order> getAllOrdersForCurrentUser();
    void persistOrder(Order order);
    Order persistOrder(SaveOrderRequest saveOrderRequest);
    List<Order> getOrdersByUserId(int userId);
    Optional<Order> getOrderById(int id);
}
