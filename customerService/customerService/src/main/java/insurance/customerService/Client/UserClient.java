package insurance.customerService.Client;

import insurance.customerService.Dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "userService") // Eureka'da kayıtlı olan isim
public interface UserClient {

    @GetMapping("/v1/user/internal/{id}")
    UserDTO getByUserId(@PathVariable("id") UUID id);
}