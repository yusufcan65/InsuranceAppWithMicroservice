package insurance.userService.Service;

import insurance.userService.Dto.Customer.CustomerUserDto;
import insurance.userService.Dto.UserAuthResponse;
import insurance.userService.Dto.UserFeignResponse;
import insurance.userService.Dto.UserRequest;
import insurance.userService.Dto.UserResponse;
import insurance.userService.Entity.Users;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    Users getUserById(UUID id);
    List<UserResponse> getUsers();
    Users getUserByUsername(String username);
    UserAuthResponse getUserForAuth(String username);
    CustomerUserDto getCustomerUserById(UUID id);
    UserFeignResponse userFeign(UUID id);

}
