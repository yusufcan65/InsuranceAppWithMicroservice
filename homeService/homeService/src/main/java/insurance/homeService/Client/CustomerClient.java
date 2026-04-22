package insurance.homeService.Client;

import insurance.homeService.Dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customerService")  // user-service değil, userService olmalı
public interface CustomerClient {

    @GetMapping("/v1/customer/internal/feign/{id}")
    CustomerResponse getCustomerForFeign(@PathVariable UUID id);
}