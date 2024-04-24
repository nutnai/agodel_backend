package agodel.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import agodel.model.UserMock;
import agodel.service.UserMockService;
import agodel.model.UserModel;
import agodel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/home")
public class HomeController {
    private UserMockService userMockService;
    private UserService userService;

    public HomeController(UserMockService userMockService,UserService userService) {
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

    @PostMapping("/login")
    public String checkUser(@RequestBody Map<String, Object> body) {
        return userService.checkUser(body);
    }
}