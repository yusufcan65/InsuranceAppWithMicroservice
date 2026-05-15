package insurance.authService.Client;

import insurance.authService.Dto.UserAuthResponse;
import insurance.insuranceCommon.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService")
public interface UserClient {

    @GetMapping("/v1/user/internal/auth/{username}")
    RestResponse<UserAuthResponse> getUserForAuth(@PathVariable String username);
}