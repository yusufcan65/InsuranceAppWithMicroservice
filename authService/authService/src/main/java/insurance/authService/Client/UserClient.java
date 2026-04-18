package insurance.authService.Client;

import insurance.authService.Dto.UserAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService")  // user-service değil, userService olmalı
public interface UserClient {

    @GetMapping("/v1/user/internal/{username}")
    UserAuthResponse getUserByUsername(@PathVariable String username);
}