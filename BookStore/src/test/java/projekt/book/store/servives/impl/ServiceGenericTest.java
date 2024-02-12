package projekt.book.store.servives.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import projekt.book.store.configuration.TestConfiguration;
import projekt.book.store.database.IBookDAO;
import projekt.book.store.database.IOrderDAO;
import projekt.book.store.database.IOrderPositionDAO;
import projekt.book.store.database.IUserRepository;
import projekt.book.store.servives.IBookService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class ServiceGenericTest {

    @Autowired
    IBookService bookService;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IUserRepository userRepository;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IOrderPositionDAO orderPositionDAO;

}
