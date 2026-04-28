package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.commons.InvalidDataException;
import mx.edu.cetys.software_quality_lab.commons.NotFoundException;
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

    PetResponse savePet(PetRequest requestPet) {
        log.info("Starting pet Request Validation, requestPet={}", requestPet);

        // Name validation
        if (requestPet.name() == null || requestPet.name().isBlank() || requestPet.name().length() < 2) {
            throw new InvalidDataException("The pet name is not valid");
        }

        // Age validation - must be greater than 0
        if (requestPet.age() == null || requestPet.age() <= 0) {
            throw new InvalidDataException("The pet age must be greater than 0");
        }

        // Color validation - must not be empty
        if (requestPet.color() == null || requestPet.color().isBlank()) {
            throw new InvalidDataException("The pet color is not valid");
        }

        var savedPet = petRepository.save(new Pet(requestPet.name(), requestPet.race(), requestPet.color(), requestPet.age()));
        return new PetResponse(savedPet.getId(), savedPet.getName(), savedPet.getColor(), savedPet.getRace(), savedPet.getAge());
    }

    public PetResponse getPetById(Long petId) {
        log.info("Starting Pet request validations, petId={}", petId);

        // petId validation
        if (petId == null || petId <= 0) {
            throw new InvalidDataException("The pet id is not valid");
        }

        var petFromDB = petRepository.findById(petId);

        if (!petFromDB.isPresent()) {
            throw new NotFoundException("The pet with id : " + petId + " wasn't found");
        }

        return getPetResponseManager(petFromDB.get());
    }

    public PetResponse updatePet(Long petId, PetRequest petUpdated) {
        log.info("Starting Pet request validations for update, petId={}", petId);

        // petId validation
        if (petId == null || petId <= 0) {
            throw new InvalidDataException("The pet id is not valid");
        }

        var petFromDB = petRepository.findById(petId);

        if (!petFromDB.isPresent()) {
            throw new NotFoundException("The pet with id : " + petId + " wasn't found");
        }

        // Name validation
        if (petUpdated.name() == null || petUpdated.name().isBlank() || petUpdated.name().length() < 2) {
            throw new InvalidDataException("The pet name is not valid");
        }

        // Age validation
        if (petUpdated.age() == null || petUpdated.age() <= 0) {
            throw new InvalidDataException("The pet age must be greater than 0");
        }

        // Color validation
        if (petUpdated.color() == null || petUpdated.color().isBlank()) {
            throw new InvalidDataException("The pet color is not valid");
        }

        Pet currPet = petFromDB.get();
        currPet.setName(petUpdated.name());
        currPet.setColor(petUpdated.color());
        currPet.setRace(petUpdated.race());
        currPet.setAge(petUpdated.age());

        return getPetResponseManager(currPet);
    }

    public PetResponse deletePet(Long petId) {
        log.info("Starting Pet request validations for delete, petId={}", petId);

        // petId validation
        if (petId == null || petId <= 0) {
            throw new InvalidDataException("The pet id is not valid");
        }

        var petFromDB = petRepository.findById(petId);

        if (!petFromDB.isPresent()) {
            throw new NotFoundException("The pet with id : " + petId + " wasn't found");
        }

        petRepository.delete(petFromDB.get());
        return getPetResponseManager(petFromDB.get());
    }

    private PetResponse getPetResponseManager(Pet realPet){
        return new PetResponse(
                realPet.getId(),
                realPet.getName(),
                realPet.getColor(),
                realPet.getRace(),
                realPet.getAge()
        );
    }




}
