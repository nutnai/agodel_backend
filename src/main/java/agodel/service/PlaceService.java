package agodel.service;

import agodel.DTO.UserDTO.GetOwnerDTO;
import agodel.DTO.UserDTO.RegisterDTO;
import agodel.DTO.PlaceDTO.*;
import agodel.data.OwnerRepository;
import agodel.model.OwnerModel;
import agodel.model.PlaceModel;
import agodel.model.RoomModel;
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
import java.util.HashMap;

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
            PlaceModel place = new PlaceModel();
            PlaceModel lastRec = placeRepository.findTopByOrderByPlaceIdDesc();
            int currentId = 1;
            if (lastRec != null) {
                currentId = Integer.parseInt(lastRec.getPlaceId()) + 1;
            }
            place.setPlaceId(String.valueOf(currentId));
            place.setName("Place Name");
            place.setAddress("Place Address");
            place.setOwner(ownerModel);
            place.setStatus("AVAILABLE");
            placeRepository.save(place);
        } catch (Exception e) {
            throw new ResponseEntityException("can't create place: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> edit(EditDTO editDTO) throws ResponseEntityException {
        PlaceModel placeModel;
        OwnerModel ownerModel;
        try {
            ownerModel = ownerRepository.findByOwnerId(editDTO.getOwnerId());
        } catch (Exception e) {
            throw new ResponseEntityException("Owner not found", HttpStatus.NOT_FOUND);
        }
        try {
            placeModel = placeRepository.findByOwnerOwnerId(ownerModel.getOwnerId());
        } catch (Exception e) {
            throw new ResponseEntityException("Place not found", HttpStatus.NOT_FOUND);
        }
        try {
            if (editDTO.getNewName() != null) {
                placeModel.setName(editDTO.getNewName());
            }
            if (editDTO.getNewAddress() != null) {
                placeModel.setAddress(editDTO.getNewAddress());
            }
            if (editDTO.getNewStatus() != null) {
                placeModel.setStatus(editDTO.getNewStatus());
            }
            entityManager.persist(placeModel);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "edit success!");
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't edit place: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<PlaceModel> testSearch(Map<String, Object> body) {
        List<PlaceModel> placeAddress = placeRepository.findByAddressContains((String) body.get("address"));
        return placeAddress;
    }

    public Map<String, Object> showDetail(GetOwnerDTO getOwnerDTO) throws ResponseEntityException {
        try {
            PlaceModel placeModel = placeRepository.findByOwnerOwnerId(getOwnerDTO.getOwnerId());
            Map<String, Object> response = new HashMap<>();
            response.put("place", placeModel);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Owner not found", HttpStatus.NOT_FOUND);
        }
    }

    public Receipt rentRoom(Map<String, Object> body) {
        String customerId = (String) body.get("customerId");
        return roomService.calPrice(body, customerId);
    }

    public Map<String, Object> search(SearchDTO searchDTO) throws ResponseEntityException {
        try {
            if (searchDTO.getKeyword() == null) {
                searchDTO.setKeyword("");
            }
            if (searchDTO.getNumberPeople() == null) {
                searchDTO.setNumberPeople(1);
            }
            if (searchDTO.getLowerPrice() == null) {
                searchDTO.setLowerPrice(0.0);
            }
            if (searchDTO.getUpperPrice() == null) {
                searchDTO.setUpperPrice(1000000000.0);
            }
            Map<String, Object> response = new HashMap<>();
            List<PlaceModel> places = placeRepository.findPlaceBySearchCriteria(
                    searchDTO.getKeyword(),
                    searchDTO.getNumberPeople(),
                    searchDTO.getLowerPrice(),
                    searchDTO.getUpperPrice());
            response.put("places", places);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't search place: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
