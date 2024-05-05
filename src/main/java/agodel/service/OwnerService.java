package agodel.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import agodel.DTO.UserDTO.*;
import agodel.data.OwnerRepository;
import agodel.exception.ResponseEntityException;
import agodel.model.OwnerModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class OwnerService {
    private OwnerRepository ownerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public void register(RegisterDTO dto, String id) throws ResponseEntityException {
        try {
            OwnerModel ownerModel = new OwnerModel();
            ownerModel.setOwnerId(id);
            ownerModel.setUsername(dto.getUsername());
            ownerModel.setFirstname(dto.getFirstname());
            ownerModel.setLastname(dto.getLastname());
            ownerModel.setPhone(dto.getPhone());
            ownerModel.setEmail(dto.getEmail());
            entityManager.persist(ownerModel);
        } catch (Exception e) {
            throw new ResponseEntityException("can't create owner: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<OwnerModel> getUser() {
        return ownerRepository.findAll();
    }

    public Map<String, Object> showDetail(GetOwnerDTO getOwnerDTO) throws ResponseEntityException {
        try {
            OwnerModel ownerModel = ownerRepository.findByOwnerId(getOwnerDTO.getOwnerId());
            Map<String, Object> response = new HashMap<>();
            response.put("owner", ownerModel);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Owner not found", HttpStatus.NOT_FOUND);
        }
    }

    public Map<String, Object> edit(EditOwnerDTO editOwnerDTO) throws ResponseEntityException {
        OwnerModel ownerModel;
        try {
            ownerModel = ownerRepository.findByOwnerId(editOwnerDTO.getOwnerId());
        } catch (Exception e) {
            throw new ResponseEntityException("Owner not found", HttpStatus.NOT_FOUND);
        }
        try {
            if (editOwnerDTO.getNewFirstname() != null) {
                ownerModel.setFirstname(editOwnerDTO.getNewFirstname());
            }
            if (editOwnerDTO.getNewLastname() != null) {
                ownerModel.setLastname(editOwnerDTO.getNewLastname());
            }
            if (editOwnerDTO.getNewPhone() != null) {
                ownerModel.setPhone(editOwnerDTO.getNewPhone());
            }
            if (editOwnerDTO.getNewEmail() != null) {
                ownerModel.setEmail(editOwnerDTO.getNewEmail());
            }
            entityManager.persist(ownerModel);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "edit success!");
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't edit owner: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
