package agodel.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import agodel.service.TestService;

@RestController
@Controller
@RequestMapping("/test")
public class TestController{

    private TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }

    @PostMapping("/1")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> body){
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getToken")
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Map<String, Object> body){
        Map<String, Object> res = new HashMap<>();
        String token = testService.get(body);
        res.put("token", token);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Map<String, Object>> showDetail(@RequestHeader Map<String, Object> header){
        Map<String, Object> res = new HashMap<>();
        String token = header.get("authorization").toString().substring(7);
        Object id = testService.validateToken(token);
        res.put("token", id);
        return ResponseEntity.ok(res);
    }
}