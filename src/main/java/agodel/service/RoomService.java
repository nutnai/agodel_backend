package agodel.service;

import agodel.DTO.PlaceDTO.ReserveDTO;
import agodel.DTO.RoomDTO.*;
import agodel.DTO.UserDTO.GetOwnerDTO;
import agodel.data.OwnerRepository;
import agodel.data.PlaceRepository;
import agodel.data.RoomRepository;
import agodel.data.PlaceRoomRepository;
import agodel.model.PlaceModel;
import agodel.model.PlaceRoomModel;
import agodel.model.RoomModel;
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

    private PlaceRoomRepository placeRoomRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public RoomService(RoomRepository roomRepository, PlaceRepository placeRepository, ReceiptService receiptService,
            OwnerRepository ownerRepository, PlaceRoomRepository placeRoomRepository) {
        this.roomRepository = roomRepository;
        this.placeRepository = placeRepository;
        this.receiptService = receiptService;
        this.ownerRepository = ownerRepository;
        this.placeRoomRepository = placeRoomRepository;
    }

    public Map<String, Object> create(CreateDTO createDTO) throws ResponseEntityException {
        String ownerId = createDTO.getOwnerId();
        PlaceModel placeModel = placeRepository.findByOwnerOwnerId(ownerId);
        if (placeModel == null) {
            throw new ResponseEntityException("Place not found", HttpStatus.NOT_FOUND);
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

            PlaceRoomModel placeRoom = new PlaceRoomModel();
            placeRoom.setPlaceId(placeModel);
            placeRoom.setRoomId(room);
            placeRoomRepository.save(placeRoom);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Error creating room", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public Map<String, Object> edit(EditDTO editDTO, String id) throws ResponseEntityException {
        RoomModel room = roomRepository.findByRoomId(editDTO.getRoomId());
        if (room == null) {
            throw new ResponseEntityException("Room not found", HttpStatus.NOT_FOUND);
        }
        PlaceRoomModel placeRoom = placeRoomRepository.findById(Long.parseLong(editDTO.getRoomId())).get();
        if (placeRoom == null) {
            throw new ResponseEntityException("Room not found in relation", HttpStatus.NOT_FOUND);
        }
        if (!placeRoom.getPlaceId().getOwner().getOwnerId().equals(id)) {
            throw new ResponseEntityException("You are not the owner of this room", HttpStatus.UNAUTHORIZED);
        }
        try {
            if (editDTO.getNewRoomType() != null) {
                room.setRoomType(editDTO.getNewRoomType());
            }
            if (editDTO.getNewFacility() != null) {
                room.setFacility(editDTO.getNewFacility());
            }
            if (editDTO.getNewNumberPeople() != null) {
                room.setNumberPeople(editDTO.getNewNumberPeople());
            }
            if (editDTO.getNewPrice() != null) {
                room.setPrice(editDTO.getNewPrice());
            }
            if (editDTO.getNewStatus() != null) {
                room.setStatus(editDTO.getNewStatus());
            }
            entityManager.merge(room);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("message", "edit success!");
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't edit room: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> showDetail(GetOwnerDTO ownerDTO) throws ResponseEntityException {
        List<RoomModel> rooms = roomRepository.findByOwnerOwnerId(ownerDTO.getOwnerId());
        if (rooms.isEmpty()) {
            throw new ResponseEntityException("Room not found", HttpStatus.NOT_FOUND);
        }
        try {
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("rooms", rooms);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Room not found", HttpStatus.NOT_FOUND);
        }
    }

    public Map<String, Object> showRoomDetail(GetRoomDTO roomDTO) throws ResponseEntityException {
        try {
            RoomModel room = roomRepository.findByRoomId(roomDTO.getRoomId());
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("room", room);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Room not found", HttpStatus.NOT_FOUND);
        }
    }

    // public List<PlaceModel> search(List<PlaceModel> place,int num) {
    // }

    public Map<String, Object> calPrice(ReserveDTO reserveDTO, Map<String, Object> payload) throws ResponseEntityException {
        RoomModel room = roomRepository.findByRoomId(reserveDTO.getRoomId());
        if (room == null) {
            throw new ResponseEntityException("Room not found", HttpStatus.NOT_FOUND);
        }
        int dayCount = reserveDTO.getDayCount();
        Double price = room.getPrice() * dayCount;
        payload.put("price", price);
        return receiptService.create(reserveDTO, payload);
    }
}
