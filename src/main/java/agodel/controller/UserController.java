package agodel.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agodel.model.UserMock;
import agodel.model.UserModel;
import agodel.service.UserMockService;
import agodel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {
    private UserMockService userMockService;
    private UserService userService;

    public UserController(UserMockService userMockService, UserService userService) {
        this.userMockService = userMockService;
        this.userService = userService;
    }

    @GetMapping
    public Map<String, String> sayHello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "GET");
        return map;
    }

    @GetMapping("/mock")
    public List<UserMock> getMockUser() {
        return userMockService.getUserMock();
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

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> body) {

        return userService.login(body);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Map<String, Object> body) {

        return userService.resetPassword(body);
    }
}