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

    public UserController(UserService userService,CustomerService customerService, OwnerService ownerService) {

        this.userService = userService;
        this.customerService = customerService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public String sayHello() {
        return "hello kuy";
    }

    @PostMapping
    public Map<String, String> sayHelloPost() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "POST");
        return map;
    }

    @PostMapping("/postTest")
    public String test(@RequestBody Map<String, Object> body){
        System.out.println("kuy");
        return (String) body.get("username");
    }

    @PostMapping("/getUser")
    public List<UserModel>  get(@RequestBody Map<String, Object> body){
        System.out.println(userService.getUser());
        return userService.getUser();
    }

    @PostMapping("/register")
    public String  register(@RequestBody Map<String, Object> body){
        return userService.register(body);
    }

//    @PostMapping("/customer")
//    public String  customer(@RequestBody Map<String, Object> body){
//        return customerService.register(body);
//    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Map<String, Object> body) {

        return userService.resetPassword(body);
    }

    @PostMapping("/login")
    public ResponseEntity<String> testLogin(@RequestBody Map<String, Object> body) {
        try {
            String id = userService.login(body);
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/getCustomer")
    public ResponseEntity<CustomerModel> testCustomer(@RequestBody Map<String, Object> body) {
        try {
            CustomerModel customer = customerService.showDetail(body);
            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/getUserDetail")
    public ResponseEntity<UserModel> getUserDetail(@RequestBody Map<String, Object> body) {
        try {
            UserModel user = userService.showDetail(body);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/getOwner")
    public ResponseEntity<OwnerModel> getOwner(@RequestBody Map<String, Object> body) {
        try {
            OwnerModel owner = ownerService.showDetail(body);
            return ResponseEntity.ok().body(owner);
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