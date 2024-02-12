package projekt.book.store.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import projekt.book.store.model.Order;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderListResponse {
    private final List<OrderDTO> orderList;
}
