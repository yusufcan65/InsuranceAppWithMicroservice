package insurance.authService.Client;

import insurance.authService.Dto.UserAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "http://localhost:8082/v1/user")
public interface UserClient {

    @GetMapping("/internal/{username}")
    UserAuthResponse getUserByUsername(@PathVariable String username);
}