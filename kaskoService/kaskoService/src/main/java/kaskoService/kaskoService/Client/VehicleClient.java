package kaskoService.kaskoService.Client;

import kaskoService.kaskoService.Dto.CarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.UUID;

@FeignClient(name = "vehicleService")
public interface VehicleClient {

    @GetMapping("/v1/vehicle/internal/feign/{id}")
    CarResponse getCarById(@PathVariable UUID id);
}
