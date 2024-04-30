package agodel.service;

import agodel.data.PlaceRepository;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
import agodel.model.RoomModel;
import org.springframework.stereotype.Service;
import agodel.data.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomService {
    private RoomRepository roomRepository;

    private PlaceRepository placeRepository;

    private ReceiptService receiptService;


    public RoomService(RoomRepository roomRepository, PlaceRepository placeRepository, ReceiptService receiptService) {
        this.roomRepository = roomRepository;
        this.placeRepository = placeRepository;
        this.receiptService = receiptService;
    }

    public String create(Map<String, Object> body) {
        RoomModel room = new RoomModel();
        RoomModel lastRec = roomRepository.findTopByOrderByRoomIdDesc();
        int currentId = Integer.parseInt(lastRec.getRoomId())+1;
        room.setRoomId(String.valueOf(currentId));
        room.setPlace(placeRepository.getReferenceById((String) body.get("place")));
        room.setBed((Integer) body.get("bed"));
        room.setFacility((String) body.get("facility"));
        room.setNumberPeople((Integer) body.get("people"));
        room.setPrice((Integer) body.get("price"));
        roomRepository.save(room);
        return "Room created successfully";
    }

    public RoomModel showDetail(Map<String, Object> body){
        return roomRepository.findByRoomId((String) body.get("roomId"));
    }

//    public List<PlaceModel> search(List<PlaceModel> place,int num) {
//    }

    public Receipt calPrice(Map<String, Object> body, String customerId){
        RoomModel thisRoom = roomRepository.findByRoomId((String) body.get("roomId"));
        int price = thisRoom.getPrice();
        return receiptService.create(body,price, (String) body.get("roomId"),customerId);
    }
}
