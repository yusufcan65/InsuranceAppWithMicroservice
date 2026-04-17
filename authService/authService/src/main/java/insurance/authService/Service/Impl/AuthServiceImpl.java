package insurance.authService.Service.Impl;

import insurance.authService.Client.UserClient;
import insurance.authService.Dto.*;
import insurance.authService.Entity.AuthUser;
import insurance.authService.Entity.Role;
import insurance.authService.Repository.AuthUserRepository;
import insurance.authService.Service.AuthService;
import insurance.authService.Service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final UserClient userClient;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthUserRepository authUserRepository,
                           UserClient userClient,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authUserRepository = authUserRepository;
        this.userClient = userClient;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {

       /* UserAuthResponse user = userClient.getUserByUsername(request.username());

        if(!user.username().equals(request.username())){
            String message = "username verification required";
            return new AuthResponse(message);

        }*/
         try {
             Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             request.username(),
                             request.password()
                     )

             );
             UserDetails userDetails = (UserDetails) authentication.getPrincipal();

             String token = jwtService.generateToken(userDetails);
             return new AuthResponse(token);

         }
         catch (Exception e){
             throw new UsernameNotFoundException("username not found");
         }



    }


    @Override
    public UserAuthResponse createAuthUser(CreateAuthUserRequest userRequest) {
        AuthUser user = new AuthUser();
        user.setUsername(userRequest.username());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(Role.ROLE_USER);

        AuthUser toSave = authUserRepository.save(user);


        UserAuthResponse userAuthResponse=
                new UserAuthResponse(toSave.getId(),toSave.getUsername());
        return userAuthResponse;
    }

    @Override
    public List<AuthUserInformationResponse> getAuthUsers() {
        List<AuthUser> authUsers = authUserRepository.findAll();
        List<AuthUserInformationResponse> authUserInformationResponses = authUsers.stream().map(this::toResponse).collect(Collectors.toList());
        return authUserInformationResponses;
    }
    private AuthUserInformationResponse toResponse(AuthUser authUser){
        AuthUserInformationResponse authUserInformationResponse = new AuthUserInformationResponse(
                authUser.getUsername(),authUser.isEnabled(),authUser.getRole()
        );
        return authUserInformationResponse;
    }




}
