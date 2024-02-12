package projekt.book.store.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import projekt.book.store.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRequest {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private User.Role role;

}
