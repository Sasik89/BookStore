package projekt.book.store.database.jdbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IOrderDAO;
import projekt.book.store.database.IOrderPositionDAO;
import projekt.book.store.database.IUserRepository;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class OrderDAO implements IOrderDAO {

    @Autowired
    Connection connection;
    @Autowired
    IOrderPositionDAO orderPositionDAO;
    @Autowired
    IUserRepository userRepository;

    @Override
    public void persistOrder(Order order) {
        try{
            String sql = "INSERT INTO torder (status, total, dateTime, user_id) VALUES (?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getStatus().toString());
            ps.setDouble(2, order.getTotal());
            ps.setTimestamp(3, Timestamp.valueOf(order.getDateTime()));
            ps.setInt(4, order.getUser().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));

            for(OrderPosition orderPosition : order.getOrderPosition()){
                this.orderPositionDAO.persist(orderPosition, order.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM torder WHERE user_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            Optional<User> userBox = this.userRepository.getById(userId);
            while(rs.next()){
                int orderId = rs.getInt("id");
                List<OrderPosition> orderPositions = this.orderPositionDAO.getByOrderId(orderId);
                Order order = new Order(
                        orderId,
                        userBox.get(),
                        Order.Status.valueOf(rs.getString("status")),
                        rs.getDouble("total"),
                        rs.getTimestamp("dateTime").toLocalDateTime()
                );
                order.getOrderPosition().addAll(orderPositions);
                result.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
       try{
           String sql = "SELECT * FROM torder WHERE id = ?";
           PreparedStatement ps = this.connection.prepareStatement(sql);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               Optional<User> userBox = this.userRepository.getById(rs.getInt("user_id"));
               Order order = new Order(
                       rs.getInt("id"),
                       userBox.get(),
                       Order.Status.valueOf(rs.getString("status")),
                       rs.getDouble("total"),
                       rs.getTimestamp("dateTime").toLocalDateTime()
               );
               List<OrderPosition> orderPositions = this.orderPositionDAO.getByOrderId(order.getId());
               order.getOrderPosition().addAll(orderPositions);
               return Optional.of(order);

           }
           return Optional.empty();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
}
