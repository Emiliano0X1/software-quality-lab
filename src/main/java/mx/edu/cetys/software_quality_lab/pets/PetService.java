package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.pets.exceptions.InvalidPetDataException;
import mx.edu.cetys.software_quality_lab.pets.exceptions.NotFoundPetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final Logger log = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    PetController.PetResponse savePet(PetController.PetRequest requestPet) {
        log.info("Starting pet Request Validation, requestPet={}", requestPet);
        //TODO VALIDATION
        //Name length > 2
        if(requestPet.name().isEmpty() || requestPet.name().length() < 2 || requestPet.name().isBlank()){
            throw  new InvalidPetDataException("Pet name is invalid");
        }
        //TODO regresar un 400 - Invalid Data si no se cumple la validacion
        //Age > 0
        //TODO regresar un 400

        // Color is not empty (extra validation from the teacher)

        var savedPet = petRepository.save(new Pet(requestPet.name(), requestPet.race(), requestPet.color(), requestPet.age()));
        return new PetController.PetResponse(savedPet.getId(),savedPet.getName(),savedPet.getColor(), savedPet.getRace(), savedPet.getAge());
    }

    public PetController.PetResponse getPetById(Long petId){
        log.info("Starting Pet request validations, petId={}", petId);
        var petFromDB = petRepository.findById(petId);

        //what if the petFromDB is null? or empty or not founf?
        //Do we throw an exception or hanlde it by the controller Advice? YES

        if(!petFromDB.isPresent()){
            throw new NotFoundPetException("The pet with id : " + petId + " wasnt found");
        }

        var realPet = petFromDB.get();
        return getPetResponseManager(realPet);

    }

    private PetController.PetResponse getPetResponseManager(Pet realPet){
        return new PetController.PetResponse(
                realPet.getId(),
                realPet.getName(),
                realPet.getColor(),
                realPet.getRace(),
                realPet.getAge()
        );
    }




}
