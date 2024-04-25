package agodel.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import agodel.data.UserRepository;
import agodel.model.UserModel;
import org.springframework.stereotype.Service;

import agodel.service.UserCountService;

@Service
public class UserService {
    private UserRepository userRepository;

    private UserCountService userCountService;

    public UserService(UserRepository userRepository, UserCountService userCountService) {

        this.userRepository = userRepository;
        this.userCountService= userCountService;
    }

    public List<UserModel> getUser() {
        return userRepository.findAll();
    }

    public Boolean checkUser(Map<String, Object> body){
        List<UserModel> user = userRepository.findByUsername((String)body.get("username"));
        if(user.isEmpty()){
            return false;
        }
        return true;
    }

    public String register(Map<String, Object> body){
        if(this.checkUser(body)){
            return "exits";
        }
        String type = (String) body.get("type");

        String id = type.equals("customer") ? userCountService.getCountCustomer(): userCountService.getCountOwner();
        UserModel user = new UserModel();
        user.setId(id);
        user.setUsername((String)body.get("username"));
        user.setPassword((String)body.get("password"));
        userRepository.save(user);
        return "good";
    }

    public String login(Map<String, Object> body){
        String username = (String)body.get("username");
        List<UserModel> users = userRepository.findByUsername(username);
        if (users.isEmpty()){
            return  "don't have this username";
        }
        String password = users.get(0).getPassword();
        if(((String)body.get("password")).equals(password)){
            return username.hashCode()+"";
        }
        return "wrong password";
    }
}
