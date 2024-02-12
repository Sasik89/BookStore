package projekt.book.store.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import projekt.book.store.database.*;

@Configuration
@ComponentScan(basePackages = {
        "projekt.book.store.services",
        "projekt.book.store.session"
})
public class TestConfiguration {


    @Bean
    public IBookDAO bookDAO(){
        return Mockito.mock(IBookDAO.class);
    }

    @Bean
    public IOrderDAO orderDAO(){
        return Mockito.mock(IOrderDAO.class);
    }

    @Bean
    public IUserRepository userRepository(){
        return Mockito.mock(IUserRepository.class);
    }

    @Bean
    public IOrderPositionDAO orderPositionDAO(){
        return Mockito.mock(IOrderPositionDAO.class);
    }
}
