package agodel.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agodel.model.UserModel;
import agodel.service.UserService;
import agodel.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private CustomerService customerService;

    public UserController(UserService userService,CustomerService customerService) {

        this.userService = userService;
        this.customerService = customerService;
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

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> body) {

        return userService.login(body);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Map<String, Object> body) {

        return userService.resetPassword(body);
    }
}