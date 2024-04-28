package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "room_id", nullable = false, length = 10)
    private String room_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private PlaceModel place_id;

    @Column(name = "bed")
    private Short bed;

    @Lob
    @Column(name = "facility")
    private String facility;

    @Column(name = "numberPeople")
    private Short numberPeople;

    @Column(name = "price")
    private Short price;

}