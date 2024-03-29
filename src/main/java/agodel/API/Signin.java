package agodel.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
public class Signin {
    
    private record SigninPayload(String email, String password) {
    }

    @RequestMapping(method = RequestMethod.POST, path = "/signin")
    public String signin(@RequestBody Map<String, String> body) {
        SigninPayload payload = new SigninPayload(body.get("email"), body.get("password"));
        System.out.println("log :\nHello " + payload.email + "!\n" + "Your password is " + payload.password + "!");
        return "Hello " + payload.email + "!\n" + "Your password is " + payload.password + "!";
    }
}
