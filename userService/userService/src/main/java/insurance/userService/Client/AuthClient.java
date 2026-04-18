package insurance.userService.Client;

import insurance.userService.Dto.Auth.CreateAuthUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authService")
public interface AuthClient {

    @PostMapping("/v1/auth/internal/create")
    void createAuthUser(@RequestBody CreateAuthUserRequest request);
}