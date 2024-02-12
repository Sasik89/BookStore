package projekt.book.store.servives.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projekt.book.store.database.IOrderDAO;
import projekt.book.store.exception.BookNotExistException;
import projekt.book.store.exception.BookValidationExpection;
import projekt.book.store.exception.UserNotExistException;
import projekt.book.store.exception.UserValidationException;
import projekt.book.store.model.Book;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.model.User;
import projekt.book.store.model.dto.OrderPositionDTO;
import projekt.book.store.model.dto.SaveOrderRequest;
import projekt.book.store.servives.IBookService;
import projekt.book.store.servives.IOrderService;
import projekt.book.store.servives.IUserService;
import projekt.book.store.session.SessionData;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {

   @Resource
    SessionData sessionData;

   @Autowired
    IOrderDAO orderDAO;

   @Autowired
    IUserService userService;

   @Autowired
    IBookService bookService;

    @Override
    public List<Order> getAllOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionData.getUser().getId());
    }

    @Override
    public void persistOrder(Order order) {
        this.orderDAO.persistOrder(order);
    }

    @Override
    public Order persistOrder(SaveOrderRequest saveOrderRequest) {

        Optional<User> userBox = this.userService.getById(saveOrderRequest.getUserId());
        if(userBox.isEmpty()){
           throw new UserNotExistException();
        }
        Order order = new Order(0, userBox.get(), saveOrderRequest.getStatus(),
                saveOrderRequest.getTotal(), saveOrderRequest.getDateTime());
        for(OrderPositionDTO orderPositionDTO : saveOrderRequest.getOrderPosition()){
            Optional<Book> bookBox = this.bookService.getBookById(orderPositionDTO.getBookId());
            if(bookBox.isEmpty()){
                throw new BookNotExistException();
            }
            OrderPosition orderPosition = new OrderPosition(0, bookBox.get(),
                    orderPositionDTO.getQuantity(), order);
            order.getOrderPosition().add(orderPosition);
        }
        this.orderDAO.persistOrder(order);
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return this.orderDAO.getOrdersByUserId(userId);
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return this.orderDAO.getOrderById(id);
    }
}
