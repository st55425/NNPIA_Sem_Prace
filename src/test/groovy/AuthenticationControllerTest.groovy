import com.example.semprace.Creator
import com.example.semprace.NnpiaSemPraceApplication
import com.example.semprace.entity.User
import com.example.semprace.repository.ReservationRepository
import com.example.semprace.security.JwtRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.*
import org.springframework.test.web.servlet.result.*




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes = NnpiaSemPraceApplication.class)
@AutoConfigureMockMvc
@Import(Creator.class)
class AuthenticationControllerTest {

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    Creator creator

    @Test
    void loginSuccess() {
        creator.saveEntity(new User(username: "user", password: "\$2y\$10\$AAgBULnreWNI/y6nbFWitOqlHlAhjeA/Ct7snibghWjMLG0WtTpq."))
        JwtRequest loginForm = new JwtRequest("user", "user");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                .content(objectMapper.writeValueAsString(loginForm))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString()
        Assertions.assertTrue(response.contains("jwttoken"))
    }

    @Test
    void loginFailed() {
        creator.saveEntity(new User(username: "user", password: "\$2y\$10\$AAgBULnreWNI/y6nbFWitOqlHlAhjeA/Ct7snibghWjMLG0WtTpq."))
        JwtRequest loginForm = new JwtRequest("user", "bad");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                .content(objectMapper.writeValueAsString(loginForm))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();

        String response = result.getResponse().getContentAsString()
        Assertions.assertFalse(response.contains("jwttoken"))
    }

}
