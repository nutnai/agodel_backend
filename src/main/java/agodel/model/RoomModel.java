package agodel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "room")
public class RoomModel {

    @Id
    @Column(name = "room_id")
    private String roomId;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private PlaceModel place;

    @Column(name = "room_type")
    private String roomType;
    private String facility;

    @Column(name = "number_people")
    private Integer numberPeople;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        AVAILABLE,
        UNAVAILABLE
    }

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    private OwnerModel owner;

    public RoomModel() {
    }

    public void setStatus(String status) {
        if (status.equals("AVAILABLE")) {
            this.status = Status.AVAILABLE;
        } else if (status.equals("UNAVAILABLE")) {
            this.status = Status.UNAVAILABLE;
        }
    }
}
