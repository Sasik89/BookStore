package projekt.book.store.servives;

import projekt.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public interface IOrderPositionService {

    List<OrderPosition> getByOrderId(int orderId);
    Optional<OrderPosition> getById(int id);

}
