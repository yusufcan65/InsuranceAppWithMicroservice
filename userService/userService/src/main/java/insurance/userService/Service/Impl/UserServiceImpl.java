package insurance.userService.Service.Impl;

import insurance.userService.Client.AuthClient;
import insurance.userService.Dto.Auth.CreateAuthUserRequest;
import insurance.userService.Dto.UserAuthResponse;
import insurance.userService.Dto.UserRequest;
import insurance.userService.Dto.UserResponse;
import insurance.userService.Entity.Users;
import insurance.userService.Repository.UserRepository;
import insurance.userService.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final AuthClient authClient;

    public UserServiceImpl(UserRepository userRepository, AuthClient authClient) {
        this.userRepository = userRepository;
        this.authClient = authClient;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        Users user = toEntity(userRequest);
        user.setStatus("ACTIVE");

        Users toSave = userRepository.save(user);

        authClient.createAuthUser(
                new CreateAuthUserRequest(
                        userRequest.username(),
                        userRequest.password()
                )
        );


        return toResponse(toSave);
    }

    @Override
    public Users getUserById(UUID id) {
        Users user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        return user;
    }

    @Override
    public List<UserResponse> getUsers() {
        List<Users> usersList = userRepository.findAll();

        return toResponseList(usersList);
    }

    @Override
    public Users getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public UserAuthResponse getUserForAuth(String username) {
        Users users = getUserByUsername(username);
        return new UserAuthResponse(
                users.getId(),
                users.getUsername(),
                users.getStatus()
        );
    }

    private Users toEntity(UserRequest userRequest){

        Users users = new Users();
        users.setName(userRequest.name());
        users.setEmail(userRequest.email());
        users.setPhone(userRequest.phone());
        users.setSurname(userRequest.surname());
        users.setUsername(userRequest.username());

        return users;

    }
    private UserResponse toResponse(Users users){
        UserResponse userResponse = new UserResponse(
                users.getId(),users.getUsername(), users.getName(),
                users.getSurname(), users.getEmail(), users.getPhone(),
                users.getStatus()
        );
        return userResponse;
    }
    private List<UserResponse> toResponseList(List<Users> usersList){
        List<UserResponse> userResponses = usersList.stream().map(
                this::toResponse).collect(Collectors.toList());
        return userResponses;
    }
}
