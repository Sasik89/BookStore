package projekt.book.store.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import projekt.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BookListResponse {
   private final List<Book> bookList;
}
