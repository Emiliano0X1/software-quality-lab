package mx.edu.cetys.software_quality_lab.pets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; //Es como postman

    @Autowired
    //Usar el repositorio de perros para las pruebas
    private PetRepository petRepository;

    @BeforeEach
    public void tearDown(){
        //Limpiar la DB despues de cada prueba
        petRepository.deleteAll();
    }

    //Create

    @Test
    void shouldCreatePetAndReturn201() throws Exception {
        String requestBody =
                """
                        {
                          "name": "Carlos Ibarra Mora",
                          "color": "Cafe",
                          "race": "Labrador",
                          "age": 6
                        }""";

        mockMvc.perform(
                //Request Set up
                post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                //Assert mode ON
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.info")
                                .value("New pet created"))
                .andExpect(jsonPath("$.response.name")
                        .value("Carlos Ibarra Mora"))
                .andExpect(jsonPath("$.response.color")
                        .value("Cafe"))
                .andExpect(jsonPath("$.response.race")
                        .value("Labrador"))
                .andExpect(jsonPath("$.response.age")
                        .value(6)



        );



    }






}
