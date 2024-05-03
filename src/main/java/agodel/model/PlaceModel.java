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
    @Column(name = "place_id")
    private String placeId;

    private String name;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    private OwnerModel owner;

    private String status;

    public PlaceModel() {
    }
}
