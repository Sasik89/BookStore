package projekt.book.store.database.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IOrderDAO;
import projekt.book.store.model.Order;
import projekt.book.store.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO implements IOrderDAO {


    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void persistOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        order.getOrderPosition().forEach(op-> op.setBook(session.merge(op.getBook())));
        try{
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM projekt.book.store.model.Order WHERE user = :user", Order.class);
        query.setParameter("user", new User(userId)); //został utworzomy konstruktor
        List<Order> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery(
                "FROM projekt.book.store.model.Order WHERE id = :id", Order.class);
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
