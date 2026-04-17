package insurance.userService.Controller;

import insurance.userService.Dto.UserAuthResponse;
import insurance.userService.Dto.UserRequest;
import insurance.userService.Dto.UserResponse;
import insurance.userService.Entity.Users;
import insurance.userService.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return  new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponses = userService.getUsers();
        return new ResponseEntity<>(userResponses,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable UUID id){
        Users user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<Users> getByUsername(@PathVariable String username){
        Users user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/internal/{username}")
    public ResponseEntity<UserAuthResponse> getUserForAuth(@PathVariable String username){
        UserAuthResponse userAuthResponse = userService.getUserForAuth(username);
        return new ResponseEntity<>(userAuthResponse,HttpStatus.OK);
    }
}
