package com.agodel.agodel.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.agodel.agodel.model.UserMock;
import com.agodel.agodel.service.UserMockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/home")
public class HomeController {
    private UserMockService userMockService;

    public HomeController(UserMockService userMockService) {
        this.userMockService = userMockService;
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
}