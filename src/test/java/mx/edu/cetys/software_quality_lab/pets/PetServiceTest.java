package mx.edu.cetys.software_quality_lab.pets;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.edu.cetys.software_quality_lab.pets.PetService;
import mx.edu.cetys.software_quality_lab.pets.PetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    //Una clase tipo Repository, es la encargada de hacer las llamadas BD
    //Se marca con el aspecto de @Repository, que Spring usa para internamente tratarla como

    //Controller : no hay logica excepto validaciones de valores de entrada

    @Mock
    PetRepository petsRepository;

    //Service Class : Es la clase donde se ejecuta el negocio / bussines rules
    @InjectMocks
    PetService petService;

    /*
    @Test
    void savePet() {

        //Arrange completado por Mockito
        var pet = new PetController.Pet("Andy", "Negro", "Cat");

        when(PetRepository.savePet(pet)).thenReturn(new PetDTO(1L,"Andy", "Negro", "Cat"));
        //Act
        PetDTO petDTO = petService.savePet(pet);
        //Assert
        verify(petsRepository.save(any(PetController.Pet.class)), times(1));
        assertEquals(1L ,petDTO.id());



        // Recibir la peticion desde el controller (Pet)
        // Verificar los valores del pet :
        // - Edad no sea negativa :
        // - Nombre mas de 2 letras
        // - Salvar a la BD, y la base datos nos regresa el mismo Pet
    }

    // TODO SAVE PET _ invalid data

    //TODO get by ID, - get of id 1- is not in DB

    @Test
    void getAllPets(){
        //Recibir la peticion desde el controller GetAll
        //Query
        //Meter valores del DTO a la respueta
        //Si la DB no tiene valores , regresar empty array
    }

     */
}
