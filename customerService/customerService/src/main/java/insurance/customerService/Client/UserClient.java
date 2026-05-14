package insurance.customerService.Client;

import insurance.customerService.Dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "userService")
public interface UserClient {

    @GetMapping("/v1/user/internal/feign/{id}")
    UserResponse getUserForFeign(@PathVariable UUID id);
}