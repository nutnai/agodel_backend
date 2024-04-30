package agodel.service;

import agodel.data.OwnerRepository;
import agodel.model.PlaceModel;
import org.springframework.stereotype.Service;
import agodel.data.PlaceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class PlaceService {
    private PlaceRepository placeRepository;
    private OwnerRepository OwnerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PlaceService(PlaceRepository placeRepository, OwnerRepository OwnerRepository) {
        this.placeRepository = placeRepository;
        this.OwnerRepository = OwnerRepository;
    }

    public String create(Map<String, Object> body) {
        try {
            PlaceModel place = new PlaceModel();
            PlaceModel lastRec = placeRepository.findTopByOrderByPlaceIdDesc();
            int currentId = Integer.parseInt(lastRec.getPlaceId())+1;
            place.setPlaceId(String.valueOf(currentId));
            place.setName((String) body.get("name"));
            place.setAddress((String) body.get("address"));
            place.setOwner(OwnerRepository.getReferenceById((String) body.get("owner")));
            placeRepository.save(place);
            return "Place created successfully";
        } catch (Exception e) {
            return "Error creating place";
        }
    }

    public String edit(Map<String, Object> body){
        try{
            PlaceModel placeModel = placeRepository.findById((String)body.get("placeId")).get();
            placeModel.setAddress((String) body.get("newAddress"));
            placeModel.setName((String) body.get("newName"));
            entityManager.merge(placeModel);
            return "edit success!";
        } catch (Exception e){
            return "error!!!";
        }
    }
}
