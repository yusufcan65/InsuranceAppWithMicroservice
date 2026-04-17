package insurance.userService.Client;

import insurance.userService.Dto.Auth.CreateAuthUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth", url = "http://localhost:8081/v1/auth")
public interface AuthClient {

    @PostMapping("/internal/create")
    void createAuthUser(@RequestBody CreateAuthUserRequest request);
}