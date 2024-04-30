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

    private int bed;
    private String facility;

    private int number_people;
    private int price;

    public RoomModel() {
    }
}
