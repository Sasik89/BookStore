package projekt.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt.book.store.exception.BookNotExistException;
import projekt.book.store.exception.UserNotExistException;
import projekt.book.store.model.Order;
import projekt.book.store.model.dto.ListResponse;
import projekt.book.store.model.dto.OrderDTO;
import projekt.book.store.model.dto.OrderListResponse;
import projekt.book.store.model.dto.SaveOrderRequest;
import projekt.book.store.servives.IOrderService;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderRestController {

    @Autowired
    IOrderService orderService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        Optional<Order> orderBox = this.orderService.getOrderById(id);
        if (orderBox.isPresent()) {
            return ResponseEntity.ok(new OrderDTO(orderBox.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ListResponse<OrderDTO> getOrderByUserId(@RequestParam int userId) {
        return new ListResponse<>(this.orderService.getOrdersByUserId(userId)
                .stream().map(OrderDTO::new).toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody SaveOrderRequest orderDTO) {
        try {
            return ResponseEntity.ok(new OrderDTO(this.orderService.persistOrder(orderDTO)));
        } catch (UserNotExistException | BookNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


