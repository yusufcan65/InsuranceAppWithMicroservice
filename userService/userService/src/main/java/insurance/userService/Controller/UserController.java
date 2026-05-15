package insurance.userService.Controller;

import insurance.insuranceCommon.RestResponse;
import insurance.userService.Dto.UserAuthResponse;
import insurance.userService.Dto.UserFeignResponse;
import insurance.userService.Dto.UserRequest;
import insurance.userService.Dto.UserResponse;
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
    public ResponseEntity<RestResponse<UserResponse>> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return  new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<RestResponse<List<UserResponse>>> getAllUsers(){
        List<UserResponse> userResponses = userService.getUsers();
        return new ResponseEntity<>(RestResponse.of(userResponses),HttpStatus.OK);
    }

    @GetMapping("/internal/auth/{username}")
    public ResponseEntity<RestResponse<UserAuthResponse>> getUserForAuth(@PathVariable String username){
        UserAuthResponse userAuthResponse = userService.getUserForAuth(username);
        return new ResponseEntity<>(RestResponse.of(userAuthResponse),HttpStatus.OK);
    }

    @GetMapping("/internal/feign/{id}")
    public ResponseEntity<RestResponse<UserFeignResponse>> getUserForFeign(@PathVariable UUID id){
        UserFeignResponse userFeignResponse = userService.userFeign(id);
        return new ResponseEntity<>(RestResponse.of(userFeignResponse),HttpStatus.OK);
    }
}
