package insurance.authService.Controller;

import insurance.authService.Dto.*;
import insurance.authService.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.login(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<AuthUserInformationResponse>> getAll(){
        List<AuthUserInformationResponse> authUserInformationResponses = authService.getAuthUsers();
        return new ResponseEntity<>(authUserInformationResponses,HttpStatus.OK);
    }

    @PostMapping("/internal/create")
    public ResponseEntity<UserAuthResponse> createUserAuth(@RequestBody CreateAuthUserRequest request){
        UserAuthResponse userAuthResponse = authService.createAuthUser(request);
        return new ResponseEntity<>(userAuthResponse,HttpStatus.OK);
    }
}
