package agodel.service;

import java.util.List;
import java.util.Map;

import agodel.data.CustomerRepository;
import agodel.data.OwnerRepository;
import agodel.model.OwnerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public String register(Map<String, Object> body,String id){
        OwnerModel owner = new OwnerModel();
        owner.setOwner_id(id);
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

}
