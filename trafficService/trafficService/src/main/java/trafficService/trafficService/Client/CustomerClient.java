package trafficService.trafficService.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import trafficService.trafficService.Dto.CustomerResponse;

import java.util.UUID;

@FeignClient(name = "customerService")
public interface CustomerClient {

    @GetMapping("/v1/customer/internal/feign/{id}")
    CustomerResponse CustomerForFeign(@PathVariable UUID id);
}