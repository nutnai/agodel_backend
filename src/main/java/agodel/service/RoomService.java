package agodel.service;

import agodel.data.PlaceRepository;
import agodel.model.RoomModel;
import org.springframework.stereotype.Service;
import agodel.data.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class RoomService {
    private RoomRepository roomRepository;

    private PlaceRepository placeRepository;


    public RoomService(RoomRepository roomRepository, PlaceRepository placeRepository) {
        this.roomRepository = roomRepository;
        this.placeRepository = placeRepository;
    }

    public String create(Map<String, Object> body) {
        RoomModel room = new RoomModel();
        RoomModel lastRec = roomRepository.findTopByOrderByRoomIdDesc();
        int currentId = Integer.parseInt(lastRec.getRoomId())+1;
        room.setRoomId(String.valueOf(currentId));
        room.setPlace(placeRepository.getReferenceById((String) body.get("place")));
        room.setBed((Integer) body.get("bed"));
        room.setFacility((String) body.get("facility"));
        room.setNumber_people((Integer) body.get("people"));
        room.setPrice((Integer) body.get("price"));
        roomRepository.save(room);
        return "Room created successfully";
    }
}
