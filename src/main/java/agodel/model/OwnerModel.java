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
@Table(name = "owner")
public class OwnerModel {

    @Id
    private String ownerID;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public OwnerModel() {
    }
}
