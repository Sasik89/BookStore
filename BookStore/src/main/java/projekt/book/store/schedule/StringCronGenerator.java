package projekt.book.store.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StringCronGenerator {

    @Scheduled(cron = "0/30 * * ? * *")
    public void generateString(){
        System.out.println("Jakaś informacja");
    }
}
