package projekt.book.store.database;

import projekt.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public interface IOrderPositionDAO {

    void persist(OrderPosition orderPosition, int orderId);

    List<OrderPosition> getByOrderId(int orderId);

    Optional<OrderPosition> getById(int id);

}
