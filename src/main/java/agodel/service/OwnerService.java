package agodel.service;

import java.util.List;
import java.util.Map;

import agodel.data.CustomerRepository;
import agodel.data.OwnerRepository;
import agodel.model.CustomerModel;
import agodel.model.OwnerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public String register(Map<String, Object> body,String id){
        OwnerModel owner = new OwnerModel();
        owner.setOwnerId(id);
        owner.setFirstname((String) body.get("firstname"));
        owner.setLastname((String) body.get("lastname"));
        owner.setPhone((String) body.get("phone"));
        owner.setEmail((String) body.get("email"));
        ownerRepository.save(owner);
        return "good";
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
