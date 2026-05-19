package insurance.kaskoService.Client;

import insurance.insuranceCommon.RestResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import insurance.kaskoService.Dto.CarResponse;
import insurance.kaskoService.Exception.ServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.UUID;

@FeignClient(name = "vehicleService")
public interface VehicleClient {

    Logger log = LoggerFactory.getLogger(VehicleClient.class);

    @GetMapping("/api/v1/vehicles/internal/{id}")
    @Retry(name = "vehicleRetry")
    @CircuitBreaker(name = "vehicleServiceCB", fallbackMethod = "vehicleFallBack")
    RestResponse<CarResponse> getCarById(@PathVariable UUID id);

    default RestResponse<CarResponse> vehicleFallBack(UUID id, Throwable e){
        log.error("Car service is not found with id :{} | message : {}",id,e.getMessage());
        throw new ServiceUnavailableException("Vehicle service is not have a response");
    }
}
