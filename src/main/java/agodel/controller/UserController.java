package agodel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agodel.model.CustomerModel;
import agodel.model.UserModel;
import agodel.model.OwnerModel;
import agodel.service.UserService;
import agodel.service.CustomerService;
import agodel.service.OwnerService;
import agodel.util.AuthenUtil;
import agodel.util.AuthenUtil.Role;
import agodel.DTO.UserDTO.*;
import agodel.exception.ResponseEntityException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private CustomerService customerService;

    private OwnerService ownerService;

    public UserController(UserService userService, CustomerService customerService, OwnerService ownerService) {

        this.userService = userService;
        this.customerService = customerService;
        this.ownerService = ownerService;
    }

    @PostMapping("/getAllUser")
    public ResponseEntity<Map<String, Object>> get(@RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ADMIN), header);
            return ResponseEntity.ok(userService.getUser());
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header);
            RegisterDTO registerDTO = new RegisterDTO(body);
            return ResponseEntity.ok(userService.register(registerDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    // ! todo: add email reset password
    @PostMapping("/resetPassword")
    public ResponseEntity<Map<String, Object>> resetPassword(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ADMIN), header);
            Map<String, Object> response = new HashMap<>();
            response.put("message", userService.resetPassword(body));
            return ResponseEntity.ok(response);
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> testLogin(@RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header);
            LoginDTO loginDTO = new LoginDTO(body);
            return ResponseEntity.ok(userService.login(loginDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/getCustomer")
    public ResponseEntity<Map<String, Object>> getCustomer(@RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header);
            GetCustomerDTO getCustomerDTO = new GetCustomerDTO(body);
            return ResponseEntity.ok(customerService.showDetail(getCustomerDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/getUserDetail")
    public ResponseEntity<Map<String, Object>> getUserDetail(@RequestHeader Map<String, Object> header,
            @RequestBody Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of("customer"), header);
            UserModel user = userService.showDetail(body);
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.ok(response);
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/getOwner")
    public ResponseEntity<Map<String, Object>> getOwner(@RequestBody Map<String, Object> body) {
        try {
            OwnerModel owner = ownerService.showDetail(body);
            Map<String, Object> response = new HashMap<>();
            response.put("owner", owner);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/editCustomer")
    public String editCustomer(@RequestBody Map<String, Object> body) {

        return customerService.edit(body);
    }

    @PostMapping("/editOwner")
    public String editOwner(@RequestBody Map<String, Object> body) {

        return ownerService.edit(body);
    }
}