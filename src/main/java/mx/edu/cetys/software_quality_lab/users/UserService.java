package mx.edu.cetys.software_quality_lab.users;

import mx.edu.cetys.software_quality_lab.pets.PetController;
import mx.edu.cetys.software_quality_lab.pets.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE
    UserController.UserResponse saveUser(UserController.UserRequest userRequest) {
        log.info("Saving user: {}", userRequest);

        if(userRequest.username().isEmpty()){
            throw new IllegalArgumentException("The username cannot be empty");
        }

        if(userRequest.username().length() < 3){
            throw new IllegalArgumentException("The username must be at least 3 characters");
        }

        if(userRequest.age() <= 0){
            throw new IllegalArgumentException("The age must be at least 1");
        }

        if(!userRequest.email().contains("@gmail.com")){
            throw new IllegalArgumentException("The email address must contain @gmail.com to be valid");
        }

        if(userRequest.password().length() < 4){
            throw new IllegalArgumentException("The password must be at least 4 characters");
        }

        if(!hasNumber(userRequest.password())){
            throw new IllegalArgumentException("The password must have a number on it");
        }

        var savedUser =  new User(userRequest.email(), userRequest.password(), userRequest.username(), userRequest.age(), userRequest.synopsis());
        userRepository.save(savedUser);
        return new UserController.UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getUsername(), savedUser.getAge(),savedUser.getSynopsis());

    }

    //READ
    List<UserController.UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserController.UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(new UserController.UserResponse(user.getId(), user.getEmail(), user.getUsername(), user.getAge(), user.getSynopsis()));
        }
        return userResponses;
    }

    UserController.UserResponse getUserByUsername(String username) {
        var foundedUser = userRepository.findByUsername(username);
        if (foundedUser == null) {
            throw new IllegalArgumentException("The user with username: " + username + " does not exist" );
        }

        return new UserController.UserResponse(foundedUser.getId(), foundedUser.getEmail(),foundedUser.getUsername(), foundedUser.getAge(), foundedUser.getSynopsis());
    }

    //UPDATE
    UserController.UserResponse changeUsername(Long user_id, String newUsername) {
        var existUser = userRepository.findById(user_id);
        if (existUser == null) {
            throw new IllegalArgumentException("The user with user_id: " + user_id + " does not exist");
        }

        if(newUsername.isEmpty()){
            throw new IllegalArgumentException("The username cannot be empty");
        }

        if(newUsername.length() < 3){
            throw new IllegalArgumentException("The username must be at least 3 characters");
        }
        User updatedUser = existUser.get();
        updatedUser.setUsername(newUsername);
        userRepository.save(updatedUser);
        return new UserController.UserResponse(updatedUser.getId(),updatedUser.getEmail(),updatedUser.getUsername(),updatedUser.getAge(),updatedUser.getSynopsis());
    }

    String changePassword(Long user_id,String newPassword) {
        var existUser = userRepository.findById(user_id);
        if (existUser == null) {
            throw new IllegalArgumentException("The user with user_id: " + user_id + " does not exist" );
        }

        if(newPassword.length() < 4){
            throw new IllegalArgumentException("The password must be at least 4 characters");
        }

        if(!hasNumber(newPassword)){
            throw new IllegalArgumentException("The password must have a number on it");
        }

        User user = existUser.get();
        user.setPassword(newPassword);
        userRepository.save(user);
        return "Password changed correctly";
    }

    //DELETE
    UserController.UserResponse deleteUser(Long user_id) {
        var existUser = userRepository.findById(user_id);
        if (existUser == null) {
            throw new IllegalArgumentException("The user with user_id: " + user_id + " does not exist" );
        }

        userRepository.delete(existUser.get());
        return new UserController.UserResponse(user_id,null,null,null,null);
    }

    private boolean hasNumber(String password){
        for (Character c : password.toCharArray()) {
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}
