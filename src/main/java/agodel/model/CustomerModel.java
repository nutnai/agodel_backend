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
@Table(name = "customer")
public class CustomerModel {

    @Id
    @Column(name = "customer_id")
    private String customerId;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public CustomerModel() {
    }
}
