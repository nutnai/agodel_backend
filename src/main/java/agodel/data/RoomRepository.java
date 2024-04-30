package agodel.data;

import agodel.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, String> {
    public RoomModel findTopByOrderByRoomIdDesc();
}
