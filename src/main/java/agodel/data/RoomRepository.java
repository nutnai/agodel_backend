package agodel.data;

import agodel.model.PlaceModel;
import agodel.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, String> {
    public RoomModel findTopByOrderByRoomIdDesc();

    public RoomModel findByRoomId(String roomId);

}