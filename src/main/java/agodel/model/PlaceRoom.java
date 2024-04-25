package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "place_room")
public class PlaceRoom {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "placeID", nullable = false)
    private PlaceModel placeID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roomID", nullable = false)
    private Room roomID;

}