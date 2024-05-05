package agodel.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import agodel.data.UserRepository;
import agodel.exception.ResponseEntityException;
import agodel.data.OwnerRepository;
import agodel.model.OwnerModel;
import agodel.model.UserModel;
import agodel.DTO.UserDTO.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import agodel.util.JwtUtil;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    private OwnerRepository ownerRepository;

    private UserCountService userCountService;

    private CustomerService customerService;

    private OwnerService ownerService;

    private PlaceService placeService;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, UserCountService userCountService,
            CustomerService customerService, OwnerService ownerService, PlaceService placeService,
            OwnerRepository ownerRepository) {

        this.userRepository = userRepository;
        this.userCountService = userCountService;
        this.customerService = customerService;
        this.ownerService = ownerService;
        this.placeService = placeService;
        this.ownerRepository = ownerRepository;
    }

    public ResponseEntity<Map<String, Object>> getUser() throws ResponseEntityException {
        try {
            Map<String, Object> response = new HashMap<>();
            List<UserModel> users = userRepository.findAll();
            response.put("users", users);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseEntityException("Error getting user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Boolean checkUser(String username) throws ResponseEntityException {
        try {
            List<UserModel> user = userRepository.findByUsername(username);
            if (user.isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new ResponseEntityException("Error checking user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String register(RegisterDTO dto) throws ResponseEntityException {
        if (this.checkUser(dto.getUsername())) {
            return "exits";
        }
        String type = dto.getType();
        String id;
        if (type.equals("customer")) {
            id = userCountService.getCountCustomer();
        } else {
            id = userCountService.getCountOwner();
        }
        UserModel user = new UserModel();
        try {
            user.setId(id);
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseEntityException("can't create user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (type.equals("customer")) {
            customerService.register(dto, id);
        } else {
            ownerService.register(dto, id);
            OwnerModel ownerModel = ownerRepository.findByOwnerId(id);
            placeService.create(dto, id, ownerModel);
        }
        return JwtUtil.generateToken(id);
    }

    public String login(Map<String, Object> body) throws Exception {
        String username = (String) body.get("username");
        List<UserModel> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new Exception("user not found");
        }
        String password = users.get(0).getPassword();
        if (((String) body.get("password")).equals(password)) {
            return JwtUtil.generateToken(users.get(0).getId());
        }
        throw new Exception("password not match");
    }

    public String resetPassword(Map<String, Object> body) {
        String newPassword = (String) body.get("newPassword");
        UserModel userModel = userRepository.findById((String) body.get("id")).get();
        userModel.setPassword(newPassword);
        entityManager.merge(userModel);
        return "password changed";

    }

    public UserModel showDetail(Map<String, Object> body) {
        String id = (String) body.get("id");
        return userRepository.findById(id).get();
    }
}
