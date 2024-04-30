package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room_image")
public class RoomImage {
    @Id
    @Column(name = "imageID", nullable = false, length = 10)
    private String imageID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roomID", nullable = false)
    private RoomModel roomModelID;

    @Column(name = "image")
    private byte[] image;

}