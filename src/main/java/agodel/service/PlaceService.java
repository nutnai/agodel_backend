package agodel.service;

import agodel.DTO.UserDTO.RegisterDTO;
import agodel.DTO.PlaceDTO.CreateDTO;
import agodel.data.OwnerRepository;
import agodel.model.OwnerModel;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
import agodel.data.PlaceRepository;
import agodel.exception.ResponseEntityException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PlaceService {
    private PlaceRepository placeRepository;
    private OwnerRepository ownerRepository;

    private RoomService roomService;

    @PersistenceContext
    private EntityManager entityManager;

    public PlaceService(PlaceRepository placeRepository, OwnerRepository ownerRepository, RoomService roomService) {
        this.placeRepository = placeRepository;
        this.ownerRepository = ownerRepository;
        this.roomService = roomService;
    }

    public void create(RegisterDTO registerDTO, String id, OwnerModel ownerModel) throws ResponseEntityException {
        try {
            CreateDTO createDTO = new CreateDTO(registerDTO.getBody());
            PlaceModel place = new PlaceModel();
            PlaceModel lastRec = placeRepository.findTopByOrderByPlaceIdDesc();
            int currentId = Integer.parseInt(lastRec.getPlaceId()) + 1;
            place.setPlaceId(String.valueOf(currentId));
            place.setName(createDTO.getName());
            place.setAddress(createDTO.getAddress());
            place.setOwner(ownerModel);
            place.setStatus("UNAVAILABLE");
            placeRepository.save(place);
        } catch (Exception e) {
            throw new ResponseEntityException("can't create place: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String edit(Map<String, Object> body) {
        try {
            String ownerId = (String) body.get("ownerId");
            OwnerModel owner = ownerRepository.findByOwnerId(ownerId);
            PlaceModel placeModel = placeRepository.findByOwnerOwnerId(ownerId);
            placeModel.setAddress((String) body.get("newAddress"));
            placeModel.setName((String) body.get("newName"));
            placeModel.setStatus((String) body.get("newStatus"));
            entityManager.merge(placeModel);
            return "edit success!";
        } catch (Exception e) {
            return "error!!!";
        }
    }

    public List<PlaceModel> testSearch(Map<String, Object> body) {
        List<PlaceModel> placeAddress = placeRepository.findByAddressContains((String) body.get("address"));
        return placeAddress;
    }

    public PlaceModel showDetail(Map<String, Object> body) {
        String ownerId = (String) body.get("ownerId");
        return placeRepository.findByOwnerOwnerId(ownerId);
    }

    public Receipt rentRoom(Map<String, Object> body) {
        String customerId = (String) body.get("customerId");
        return roomService.calPrice(body, customerId);
    }

    public List<PlaceModel> search(Map<String, Object> body) {
        return placeRepository.findPlaceBySearchCriteria((String) body.get("address"),
                (Integer) body.get("numberPeople"), (Integer) body.get("lowerPrice"), (Integer) body.get("upperPrice"));
    }

}
