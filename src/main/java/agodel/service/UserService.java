package agodel.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import agodel.data.UserRepository;
import agodel.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    private UserCountService userCountService;

    private CustomerService customerService;

    private OwnerService ownerService;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, UserCountService userCountService, CustomerService customerService, OwnerService ownerService) {

        this.userRepository = userRepository;
        this.userCountService = userCountService;
        this.customerService = customerService;
        this.ownerService = ownerService;
    }

    public List<UserModel> getUser() {
        return userRepository.findAll();
    }

    public Boolean checkUser(Map<String, Object> body) {
        List<UserModel> user = userRepository.findByUsername((String) body.get("username"));
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }

    public String register(Map<String, Object> body) {
        if (this.checkUser(body)) {
            return "exits";
        }
        String type = (String) body.get("type");
        String id;
        if(type.equals("customer")){
            id =  userCountService.getCountCustomer();
        }
        else{
            id =  userCountService.getCountOwner();
        }
        UserModel user = new UserModel();
        user.setId(id);
        user.setUsername((String) body.get("username"));
        user.setPassword((String) body.get("password"));
        userRepository.save(user);
        if(type.equals("customer")){
            customerService.register(body,id);
        }
        else{
            ownerService.register(body,id);
        }
        return "good";
    }

    public String login(Map<String, Object> body) {
        String username = (String) body.get("username");
        List<UserModel> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            return "don't have this username";
        }
        String password = users.get(0).getPassword();
        if (((String) body.get("password")).equals(password)) {
            return users.get(0).getId();
        }
        return "wrong password";
    }

    public String resetPassword(Map<String, Object> body) {
        String newPassword = (String) body.get("newPassword");
        UserModel userModel = userRepository.findById((String) body.get("id")).get();
        userModel.setPassword(newPassword);
        entityManager.merge(userModel);
        return "password changed";

    }
}
