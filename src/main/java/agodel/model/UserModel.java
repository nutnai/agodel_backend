package agodel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class UserModel {

    @Id
    private String id;

    private String username;
    private String password;

    public UserModel() {
    }
}
