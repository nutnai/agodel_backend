package agodelTest.controllerTest.UserControllerTest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import agodel.controller.UserController;

@WebMvcTest(controllers = UserController.class)
class getUserTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getValid() throws Exception{
        this.mvc.perform(get("/user/getAllUser"))
        .andExpect(status().isForbidden());
    }

}
