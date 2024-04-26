package agodel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "place")
public class PlaceModel {

    @Id
    private String placeId;

    private String name;
    private String address;

    @OneToOne
    @JoinColumn(name = "ownerID", referencedColumnName = "ownerID")
    private OwnerModel owner;

    public PlaceModel() {
    }
}
