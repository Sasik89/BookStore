package projekt.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IOrderDAO;
import projekt.book.store.database.sequence.IOrderIdSequence;
import projekt.book.store.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//@Repository
public class OrderDAO implements IOrderDAO {

    @Autowired
    IOrderIdSequence orderIdSequence;

    public final List<Order> orders = new ArrayList<>();

    @Override
    public void persistOrder(Order order) {
        order.setId(this.orderIdSequence.getId());
        this.orders.add(order);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
/*        List<Order> result = new ArrayList<>();
        for(Order order : this.orders){
            if(order.getUser().getId() == userId){
                result.add(order);
            }
        }
        return result;*/
       return this.orders.stream().filter(o -> o.getUser().getId() == userId).toList();
    }

    @Override
    public Optional<Order> getOrderById(int id) {
/*        for(Order order : this.orders) {
            if(order.getId() == id) {
                return Optional.of(order);
            }
        }
        return Optional.empty();*/
       return this.orders.stream().filter(o-> o.getId() == id).findFirst();
    }
}
