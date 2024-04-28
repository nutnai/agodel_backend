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
    @Column(name = "image_id", nullable = false, length = 10)
    private String image_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private PlaceModel place_id;

    @Column(name = "image")
    private byte[] image;

}