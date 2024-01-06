package projekt.book.store.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;
    private String isbn;
}

