package projekt.book.store.servives.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.book.store.database.IOrderDAO;
import projekt.book.store.model.Order;
import projekt.book.store.servives.IOrderService;
import projekt.book.store.session.SessionData;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {

   @Resource
    SessionData sessionData;

   @Autowired
    IOrderDAO orderDAO;

    @Override
    public List<Order> getAllOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionData.getUser().getId());
    }
}
