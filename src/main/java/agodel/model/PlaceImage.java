package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "place_image")
public class PlaceImage {
    @Id
    @Column(name = "imageID", nullable = false, length = 10)
    private String imageID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "placeID", nullable = false)
    private PlaceModel placeID;

    @Column(name = "image")
    private byte[] image;

}