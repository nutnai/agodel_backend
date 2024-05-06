package agodel.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "owner")
public class FacilityModel {

    @Id
    @Column(name = "facility_id")
    private String facilityId;

    private String name;

    private String description;

    public FacilityModel() {
    }
}
