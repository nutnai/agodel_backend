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
    @Column(name = "roomID", nullable = false, length = 10)
    private String roomID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeID")
    private PlaceModel placeID;

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