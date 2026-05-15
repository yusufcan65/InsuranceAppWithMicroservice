package insurance.authService.Controller;

import insurance.authService.Dto.*;
import insurance.authService.Service.AuthService;
import insurance.insuranceCommon.RestResponse;
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
    public ResponseEntity<RestResponse<AuthResponse>> login(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.login(loginRequest);
        return new ResponseEntity<>(RestResponse.of(authResponse), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<RestResponse<List<AuthUserInformationResponse>>> getAll(){
        List<AuthUserInformationResponse> authUserInformationResponses = authService.getAuthUsers();
        return new ResponseEntity<>(RestResponse.of(authUserInformationResponses),HttpStatus.OK);
    }

    @PostMapping("/internal/create")
    public ResponseEntity<RestResponse<UserAuthResponse>> createUserAuth(@RequestBody CreateAuthUserRequest request){
        UserAuthResponse userAuthResponse = authService.createAuthUser(request);
        return new ResponseEntity<>(RestResponse.of(userAuthResponse),HttpStatus.OK);
    }
}
