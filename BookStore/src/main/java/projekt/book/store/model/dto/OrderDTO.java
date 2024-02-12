package projekt.book.store.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projekt.book.store.controllers.rest.RestConstans;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;
import projekt.book.store.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {



    private int id;

    private String user;
    private Set<String> orderPosition = new HashSet<>();
    private Order.Status status;
    private double total;
    private LocalDateTime dateTime;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.user = RestConstans.API_LOCATION+ "user/" + order.getUser().getId();
        order.getOrderPosition()
                .forEach(op-> this.orderPosition
                        .add(RestConstans.API_LOCATION + "order-position/" + op.getId()));
            this.status = order.getStatus();
            this.total = order.getTotal();
            this.dateTime = order.getDateTime();
             }
    }
