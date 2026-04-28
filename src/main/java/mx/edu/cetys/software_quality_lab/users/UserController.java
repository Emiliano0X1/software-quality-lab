package mx.edu.cetys.software_quality_lab.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    //HTTP Verbs : POST , GET, PUT, PATCH, DELETE
    // GET localhost : 8080 /users --- TODOS los users, TODO pagination
    // GET localhost : 8080 /users/{username} --- User by username
    // POST localhost : 8080 /users/create --- Nuevo User - Request Body {json body}
    // PATCH localhost : 8080 /users/{user_id}?username=? -- Actualizar Username by id
    // PATCH localhost : 8080 /users/{user_id}?password=? -- Actualizar password by id
    // DELETE localhost : 8080 /users/{user_id}

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //Response Generic Wrapper to include info in all APIs
    record ApiResponse<T> (String message, T data, String error) {}
    public record UserWrapper(UserResponse user) {}

    //CREATE METHODS
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<UserWrapper> createUser(@RequestBody UserRequest user) {
        try{
            return new ApiResponse<>("New user created", new UserWrapper(userService.saveUser(user)), null);
        } catch (Exception e){
            return new ApiResponse<>("Failed to create user", null , e.getMessage());
        }
    }


    //READ METHODS
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<UserWrapper>> getUsers() {
        try{
            List<UserResponse> usersDTOs = userService.getAllUsers();
            List<UserWrapper> usersWrappers = new ArrayList<>();

            for (UserResponse user : usersDTOs) {
                UserWrapper userWrapper = new UserWrapper(user);
                usersWrappers.add(userWrapper);
            }
            return new ApiResponse<>("List of users", usersWrappers, null);

        } catch (Exception e){
            return new ApiResponse<>("Cant get list of users", null , e.getMessage());
        }
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserWrapper> getUserByUsername(@PathVariable("username") String username) {
        try{
            return new ApiResponse<>("User by Username correct", new UserWrapper(userService.getUserByUsername(username)), null);
        } catch (Exception e){
            return new ApiResponse<>("Something went wrong", null , e.getMessage());
        }
    }

    //UPDATE
    @PatchMapping("/edit-username/{user_id}")
    ApiResponse<UserWrapper> updateUser(@PathVariable("user_id") Long userId, @RequestParam ("username") String username) {
        try{
            return new ApiResponse<>("Username edited correct", new UserWrapper(userService.changeUsername(userId,username)), null);
        } catch (Exception e){
            return new ApiResponse<>("Something went wrong to edit username", null , e.getMessage());
        }
    }

    @PatchMapping("/edit-password/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ApiResponse<String> updatePassword(@PathVariable("user_id") Long userId, @RequestParam ("password") String password) {
        try{
            return new ApiResponse<>("New password setted", userService.changePassword(userId,password), null);
        } catch (Exception e){
            return new ApiResponse<>("Something went wrong to update password", null , e.getMessage());
        }
    }

    //DELETE
    @DeleteMapping("/delete-user/{user_id}")
    ApiResponse<UserWrapper> deleteUser(@PathVariable("user_id") Long userId) {
        try{
            return new ApiResponse<>("User deleted", new UserWrapper(userService.deleteUser(userId)), null);
        } catch (Exception e){
            return new ApiResponse<>("Something went wrong to delete user", null , e.getMessage());
        }
    }


}
