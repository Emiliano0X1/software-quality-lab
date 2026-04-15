package mx.edu.cetys.software_quality_lab.users;

import mx.edu.cetys.software_quality_lab.pets.PetRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; //Es como postman

    @Autowired
    //Usar el repositorio de perros para las pruebas
    private UserRepository userRepository;

    @BeforeEach
    public void tearDown(){
        //Limpiar la DB despues de cada prueba
        userRepository.deleteAll();
    }


    //Post - HappyPath
    @Test
    @DisplayName("Create User - HAPPY PATH")
    void shouldCreateUserAndReturn201() throws Exception {
        String requestBody =
                """
                        {
                          "email": "photo@gmail.com",
                          "password": "jump67",
                          "username": "DAYONE",
                          "age": 19,
                          "synopsis" : "Wake up in day one"
                        }""";

        mockMvc.perform(
                        //Request Set up
                        post("/api/v1/users/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                        //Assert mode ON
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message")
                        .value("New user created"))
                .andExpect(jsonPath("$.data.user.email")
                        .value("photo@gmail.com"))
                .andExpect(jsonPath("$.data.user.username")
                        .value("DAYONE"))
                .andExpect(jsonPath("$.data.user.age")
                        .value(19))
                .andExpect(jsonPath("$.data.user.synopsis")
                        .value("Wake up in day one")
                );
    }

    @Test
    @DisplayName("GET - Happy path")
    void shouldGetAllUsers() throws Exception {

        User user = new User();
        user.setEmail("photo@gmail.com");
        user.setPassword("jump67");
        user.setUsername("DAYONE");
        user.setAge(19);
        user.setSynopsis("Wake up in day one");
        userRepository.save(user);

        mockMvc.perform(
                get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("List of users")
                )
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].user.email")
                        .value("photo@gmail.com"))
                .andExpect(jsonPath("$.data[0].user.username")
                        .value("DAYONE"))
                .andExpect(jsonPath("$.data[0].user.age")
                        .value(19))
                .andExpect(jsonPath("$.data[0].user.synopsis")
                        .value("Wake up in day one")
                );
    }
}
