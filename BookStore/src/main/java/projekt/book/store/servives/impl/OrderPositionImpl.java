package projekt.book.store.servives.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.book.store.database.IOrderPositionDAO;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.servives.IOrderPositionService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderPositionImpl implements IOrderPositionService {

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Override
    public List<OrderPosition> getByOrderId(int orderId) {
        return this.orderPositionDAO.getByOrderId(orderId);
    }

    @Override
    public Optional<OrderPosition> getById(int id) {
        return this.orderPositionDAO.getById(id);
    }
}
