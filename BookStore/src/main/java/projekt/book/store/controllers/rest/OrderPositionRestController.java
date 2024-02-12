package projekt.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.model.dto.ListResponse;
import projekt.book.store.model.dto.OrderPositionListResponse;
import projekt.book.store.model.dto.OrderPositionResponseDTO;
import projekt.book.store.servives.IOrderPositionService;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/order-position")
public class OrderPositionRestController {

    @Autowired
    IOrderPositionService orderPositionService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderPositionResponseDTO> getById(@PathVariable int id){
        Optional<OrderPosition> orderPositionBox = this.orderPositionService.getById(id);
        return orderPositionBox.map(op->ResponseEntity.ok(new OrderPositionResponseDTO(op)))
                .orElseGet(()->ResponseEntity.notFound().build());        }

    @RequestMapping(method = RequestMethod.GET)
    public ListResponse<OrderPositionResponseDTO> getByOrderId(@RequestParam int orderId){
        return new ListResponse<>(this.orderPositionService.getByOrderId(orderId).stream()
                .map(OrderPositionResponseDTO::new).toList());
    }

}
