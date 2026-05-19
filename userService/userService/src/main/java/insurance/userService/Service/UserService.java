package insurance.userService.Service;

import insurance.userService.Dto.*;
import insurance.userService.Entity.Users;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UUID id,UpdateUserRequest request);
    Users getUserById(UUID id);
    List<UserResponse> getUsers();
    Users getUserByUsername(String username);
    UserAuthResponse getUserForAuth(String username);
    UserFeignResponse userFeign(UUID id);


}
