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
    @Column(name = "image_id", nullable = false, length = 10)
    private String image_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room_id;

    @Column(name = "image")
    private byte[] image;

}