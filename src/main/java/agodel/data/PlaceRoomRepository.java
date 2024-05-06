package agodel.data;

import agodel.model.PlaceRoomModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRoomRepository extends JpaRepository<PlaceRoomModel, Long> {

}
