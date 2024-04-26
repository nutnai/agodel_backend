package agodel.service;

import agodel.data.UserCountRepository;
import agodel.model.UserCountModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCountService {
    private UserCountRepository userCountRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UserCountService(UserCountRepository userCountRepository){
        this.userCountRepository = userCountRepository;
    }

    public String getCountCustomer(){
        UserCountModel userCountModel = userCountRepository.findById("customer").get();
        String id = "1" + String.format("%07d", userCountModel.getCount());
        userCountModel.setCount(userCountModel.getCount()+1);
        entityManager.merge(userCountModel);
        return id;
    }

    public String getCountOwner(){
        UserCountModel userCountModel = userCountRepository.findById("owner").get();
        String id = "2" + String.format("%07d", userCountModel.getCount());
        userCountModel.setCount(userCountModel.getCount()+1);
        entityManager.merge(userCountModel);
        return id;
    }
}
