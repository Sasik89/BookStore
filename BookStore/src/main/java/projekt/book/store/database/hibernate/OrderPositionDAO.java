package projekt.book.store.database.hibernate;

import jakarta.persistence.NoResultException;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IOrderPositionDAO;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderPositionDAO implements IOrderPositionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    OrderDAO orderDAO;

    @Override
    public void persist(OrderPosition orderPosition, int orderId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<OrderPosition> getByOrderId(int orderId) {
        Optional<Order> orderBox = this.orderDAO.getOrderById(orderId);
        if(orderBox.isPresent()){
            return new ArrayList<>(orderBox.get().getOrderPosition());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<OrderPosition> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<OrderPosition> query = session.createQuery(
                "FROM projekt.book.store.model.OrderPosition WHERE id = :id",OrderPosition.class);
        query.setParameter("id", id);
        try{
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
