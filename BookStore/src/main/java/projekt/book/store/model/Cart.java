package projekt.book.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class Cart {

    private final Set<OrderPosition> positions = new HashSet<>();

}
