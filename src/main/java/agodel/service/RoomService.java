package agodel.service;

import agodel.DTO.RoomDTO.CreateDTO;
import agodel.data.OwnerRepository;
import agodel.data.PlaceRepository;
import agodel.model.OwnerModel;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
import agodel.model.RoomModel;
import agodel.data.RoomRepository;
import agodel.exception.ResponseEntityException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomService {
    private RoomRepository roomRepository;

    private PlaceRepository placeRepository;

    private ReceiptService receiptService;

    private OwnerRepository ownerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public RoomService(RoomRepository roomRepository, PlaceRepository placeRepository, ReceiptService receiptService,
            OwnerRepository ownerRepository) {
        this.roomRepository = roomRepository;
        this.placeRepository = placeRepository;
        this.receiptService = receiptService;
        this.ownerRepository = ownerRepository;
    }

    public Map<String, Object> create(CreateDTO createDTO) throws ResponseEntityException {
        String ownerId = createDTO.getOwnerId();
        PlaceModel placeModel;
        try {
            placeModel = placeRepository.findByOwnerOwnerId(ownerId);
        } catch (Exception e) {
            throw new ResponseEntityException("Owner not found", HttpStatus.NOT_FOUND);
        }
        try {
            RoomModel room = new RoomModel();
            RoomModel lastRec = roomRepository.findTopByOrderByRoomIdDesc();
            int currentId = 1;
            if (lastRec != null) {
                currentId = Integer.parseInt(lastRec.getRoomId()) + 1;
            }
            room.setRoomId(String.valueOf(currentId));
            String placeId = placeModel.getPlaceId();
            room.setPlace(placeRepository.findByPlaceId(placeId));
            room.setOwner(ownerRepository.findByOwnerId(ownerId));
            room.setRoomType(createDTO.getRoomType());
            room.setFacility(createDTO.getFacility());
            room.setNumberPeople(createDTO.getNumberPeople());
            room.setPrice(createDTO.getPrice());
            room.setStatus(createDTO.getStatus());
            roomRepository.save(room);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("room", room);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Error creating room", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public String edit(Map<String, Object> body) {
        try {
            String roomId = (String) body.get("roomId");
            RoomModel room = roomRepository.findByRoomId(roomId);
            room.setFacility((String) body.get("newFacility"));
            room.setRoomType((String) body.get("newType"));
            room.setStatus((String) body.get("newStatus"));
            room.setPrice((Integer) body.get("newPrice"));
            room.setNumberPeople((Integer) body.get("newNumber"));
            entityManager.merge(room);
            return "edit success!";
        } catch (Exception e) {
            return "error!!!";
        }
    }

    public List<RoomModel> showDetail(Map<String, Object> body) {
        return roomRepository.findByOwnerOwnerId((String) body.get("ownerId"));
    }

    public RoomModel showRoomDetail(Map<String, Object> body) {
        return roomRepository.findByRoomId((String) body.get("roomId"));
    }

    // public List<PlaceModel> search(List<PlaceModel> place,int num) {
    // }

    public Receipt calPrice(Map<String, Object> body, String customerId) {
        RoomModel thisRoom = roomRepository.findByRoomId((String) body.get("roomId"));
        int dayCount = (Integer) body.get("dayCount");
        int price = thisRoom.getPrice() * dayCount;
        return receiptService.create(body, price, (String) body.get("roomId"), customerId);
    }
}
