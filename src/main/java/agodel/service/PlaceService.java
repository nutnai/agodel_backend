package agodel.service;

import agodel.data.OwnerRepository;
import agodel.model.OwnerModel;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
import org.springframework.stereotype.Service;
import agodel.data.PlaceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

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

    public String create(Map<String, Object> body,String id, OwnerModel ownerModel) {
        try {
            PlaceModel place = new PlaceModel();
            PlaceModel lastRec = placeRepository.findTopByOrderByPlaceIdDesc();
            int currentId = Integer.parseInt(lastRec.getPlaceId())+1;
            place.setPlaceId(String.valueOf(currentId));
            place.setName("Enter name");
            place.setAddress("Enter address");
            place.setOwner(ownerModel);
            place.setStatus("Private");
            placeRepository.save(place);
            return "Create place success";
        } catch (Exception e) {
            return "Error creating place";
        }
    }

    public String edit(Map<String, Object> body){
        try{
            String ownerId = (String) body.get("ownerId");
            OwnerModel owner = ownerRepository.findByOwnerId(ownerId);
            PlaceModel placeModel = placeRepository.findByOwnerOwnerId(ownerId);
            placeModel.setAddress((String) body.get("newAddress"));
            placeModel.setName((String) body.get("newName"));
            placeModel.setStatus((String) body.get("newStatus"));
            entityManager.merge(placeModel);
            return "edit success!";
        } catch (Exception e){
            return "error!!!";
        }
    }

    public List<PlaceModel> testSearch(Map<String, Object> body){
        List<PlaceModel> placeAddress = placeRepository.findByAddressContains((String) body.get("address"));
        return placeAddress;
    }

    public PlaceModel showDetail(Map<String, Object> body){
        String ownerId = (String) body.get("ownerId");
        return placeRepository.findByOwnerOwnerId(ownerId);
    }

    public Receipt rentRoom(Map<String, Object> body){
        String customerId = (String) body.get("customerId");
        return roomService.calPrice(body,customerId);
    }

    public List<PlaceModel> search(Map<String, Object> body){
        return placeRepository.findPlaceBySearchCriteria((String) body.get("address"), (Integer) body.get("numberPeople"),(Integer) body.get("lowerPrice"),(Integer) body.get("upperPrice"));
    }

}
