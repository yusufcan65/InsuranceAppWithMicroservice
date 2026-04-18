package insurance.authService.Client;

import insurance.authService.Dto.UserAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/internal/{username}")
    UserAuthResponse getUserByUsername(@PathVariable String username);
}