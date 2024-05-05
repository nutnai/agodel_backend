package agodel.service;

import java.util.List;
import java.util.Map;

import agodel.DTO.UserDTO.RegisterDTO;
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

    public void register(RegisterDTO dto, String id) throws ResponseEntityException{
        try {
            OwnerModel ownerModel = new OwnerModel();
            ownerModel.setOwnerId(id);
            ownerModel.setFirstname(dto.getFirstname());
            ownerModel.setLastname(dto.getLastname());
            ownerModel.setPhone(dto.getPhone());
            ownerModel.setEmail(dto.getEmail());
            entityManager.persist(ownerModel);
        } catch (Exception e){
            throw new ResponseEntityException("can't create owner: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<OwnerModel> getUser() {
        return ownerRepository.findAll();
    }

    public OwnerModel showDetail(Map<String, Object> body){
        return ownerRepository.findByOwnerId((String) body.get("ownerId"));
    }

    public String edit(Map<String, Object> body){
        try{
            OwnerModel ownerModel = ownerRepository.findByOwnerId((String) body.get("ownerId"));
            ownerModel.setFirstname((String) body.get("newFirstName"));
            ownerModel.setLastname((String) body.get("newLastName"));
            ownerModel.setPhone((String) body.get("newPhone"));
            ownerModel.setEmail((String) body.get("newEmail"));
            entityManager.merge(ownerModel);
            return "edit success!";
        } catch (Exception e){
            return "error!!!";
        }
    }

}
