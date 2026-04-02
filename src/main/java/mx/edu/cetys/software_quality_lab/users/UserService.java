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
        //username length
        //age limits
        //email validation Just have @ and .com ending
        //password length and has a number

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

        //TODO Validate newUserName
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

        //TODO validate new Password
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
}
