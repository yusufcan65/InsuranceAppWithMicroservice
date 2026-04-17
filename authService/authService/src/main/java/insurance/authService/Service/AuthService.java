package insurance.authService.Service;

import insurance.authService.Dto.*;

import java.util.List;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    UserAuthResponse createAuthUser(CreateAuthUserRequest userRequest);
    List<AuthUserInformationResponse> getAuthUsers();
}
