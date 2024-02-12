package projekt.book.store.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import projekt.book.store.model.OrderPosition;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderPositionListResponse {

    private final List<OrderPositionResponseDTO> orderList;

}
