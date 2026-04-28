package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.commons.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    //HTTP Verbs : POST , GET, PUT, PATCH, DELETE
    // GET localhost : 8080 /pets --- TODOS los pets, TODO pagination
    // GET localhost : 8080 /pets/{id} --- Pet by ID
    // POST localhost : 8080 /pets --- Nuevo Pet - Request Body {json body}
    // PUT localhost : 8080 /pets/{id} -- Actualizar PET by ID
    // DELETE localhost : 8080 / pets /{id} --- /Flag available yes/no

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    //DTO (Data Transfer Object)
    record PetRequest(String name, String color, String race, Integer age){};
    record PetResponse(Long id, String name, String color, String race, Integer age){};

    //Response Generic Wrapper to include info in all APIs
    public record PetWrapper(PetResponse pet){}

    @GetMapping("/help")
    ApiResponse<PetWrapper> help(){
        return new ApiResponse<>("This is the help API", null,null);
    }

    @GetMapping("/{petId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse <PetWrapper> findPetById(@PathVariable("petId") Long petId){
        var pet = petService.getPetById(petId);
        return new ApiResponse<>("Pet found", new PetWrapper(pet),null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<PetWrapper> createPet(@RequestBody PetController.PetRequest requestPet){
        return new ApiResponse<>("New pet created", new PetWrapper(petService.savePet(requestPet)),null);
    }

    //TODO - PUT AND DELETE







}
