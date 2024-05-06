package agodel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agodel.model.RoomModel;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomModel, String> {

}
