package agodel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "age")
    private int age;

    public UserModel() {
    }

    public UserModel(int id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }
}
