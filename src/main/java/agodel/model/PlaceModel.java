package agodel.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String address;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    private OwnerModel owner;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        AVAILABLE,
        UNAVAILABLE
    }

    public PlaceModel() {
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
}
