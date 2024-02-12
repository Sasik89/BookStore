package projekt.book.store.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projekt.book.store.controllers.rest.RestConstans;
import projekt.book.store.model.Book;
import projekt.book.store.model.Order;
import projekt.book.store.model.OrderPosition;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionResponseDTO {

    private int id;
    private String book;
    private int quantity;
    private String order;

    public OrderPositionResponseDTO(OrderPosition orderPosition){
        this.id = orderPosition.getId();
        this.book = RestConstans.API_LOCATION + "book/" + orderPosition.getBook().getId();
        this.quantity = orderPosition.getQuantity();
        this.book = RestConstans.API_LOCATION + "book/" + orderPosition.getOrder().getId();
    }

}
