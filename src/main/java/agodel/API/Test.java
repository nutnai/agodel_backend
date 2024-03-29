package agodel.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import agodel.model.*;
import agodel.repository.*;

@RestController
public class Test {

    @Value("${test.value}") 
    private String test;

    @Autowired
    private UserRepo userRepo;

    public record Package(String massage) {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public UserModel get() {
        String name = userRepo.findByAge(20).get(0).getFirstname();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/test")
    public Package post(@RequestBody Map<String, Object> body) {
        Map<String, Object> ids = (Map<String, Object>) body.get("id");
        return new Package("Hello " + ids.get("a") + " " + ids.get("b"));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    public UserModel getUser(@PathVariable int id) {
        return userRepo.findById(id).get();
    }
}
