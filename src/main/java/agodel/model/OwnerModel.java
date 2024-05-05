package agodel.model;

import jakarta.persistence.Column;
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
    @Column(name = "owner_id")
    private String ownerId;

    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public OwnerModel() {
    }
}
