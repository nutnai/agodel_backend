package agodel.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import agodel.data.UserRepository;
import agodel.model.UserModel;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getUser() {
        return userRepository.findAll();
    }

    public String checkUser(Map<String, Object> body){
        List<UserModel> user = userRepository.findByUsername((String)body.get("username"));
        if(user.isEmpty()){
            return "error kuy";
        }
        return "ok";
    }
}
