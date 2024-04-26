package agodel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "user_count")
public class UserCountModel {

    @Id
    private String type;
    private int count;

    public UserCountModel() {
    }
}
