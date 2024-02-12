package projekt.book.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "torder")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderPosition> orderPosition = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Status status;
    private double total;
    private LocalDateTime dateTime;


    public Order(int id) {
        this.id = id;
    }

    public String getPrettyTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return this.dateTime.format(formatter);

    }

    public void addOrderPossition(OrderPosition orderPosition){
        this.orderPosition.add(orderPosition);
        orderPosition.setOrder(this);
    }

    public Order(int id, User user, Status status, double total, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.total = total;
        this.dateTime = dateTime;
    }

    public enum Status {
        NEW,
        PAID,
        SENT,
        Done,
    }

}
