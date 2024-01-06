package projekt.book.store.database.sequence;

import org.springframework.stereotype.Component;
import projekt.book.store.servives.IBookService;

@Component
public class BookIdSequence implements IBookIdSequence {


    private int id = 0;

    @Override
    public int getId(){
        return ++id;
    }


}
