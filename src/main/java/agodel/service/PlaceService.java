package agodel.service;

import agodel.data.OwnerRepository;
import agodel.model.PlaceModel;
import org.springframework.stereotype.Service;
import agodel.data.PlaceRepository;

import java.util.Map;

@Service
public class PlaceService {
    final PlaceRepository placeRepository;
    final OwnerRepository OwnerRepository;

    public PlaceService(PlaceRepository placeRepository, OwnerRepository OwnerRepository) {
        this.placeRepository = placeRepository;
        this.OwnerRepository = OwnerRepository;
    }

    public String create(String token, Map<String, Object> body) {
        try {
            PlaceModel place = new PlaceModel();
            place.setName((String) body.get("name"));
            place.setAddress((String) body.get("address"));
            place.setOwner(OwnerRepository.getReferenceById((String) body.get("owner")));
            placeRepository.save(place);
            return "Place created successfully";
        } catch (Exception e) {
            return "Error creating place";
        }
    }
}
