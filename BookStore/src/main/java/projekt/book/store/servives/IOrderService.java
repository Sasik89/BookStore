package projekt.book.store.servives;

import projekt.book.store.model.Order;

import java.util.List;

public interface IOrderService {


    List<Order> getAllOrdersForCurrentUser();
}
