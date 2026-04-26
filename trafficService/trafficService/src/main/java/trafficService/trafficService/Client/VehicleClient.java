package trafficService.trafficService.Client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import trafficService.trafficService.Dto.CarResponse;
import trafficService.trafficService.Exception.ServiceUnavailableException;

import java.util.UUID;

@FeignClient(name = "vehicleService")
public interface VehicleClient {

    Logger log = LoggerFactory.getLogger(VehicleClient.class);

    @GetMapping("/v1/vehicle/internal/feign/{id}")
    @Retry(name = "vehicleRetry")
    @CircuitBreaker(name = "vehicleServiceCB", fallbackMethod = "vehicleFallBack")
    CarResponse getCarById(@PathVariable UUID id);

    default CarResponse vehicleFallBack(UUID id, Throwable e){
        log.error("Car service is not found with id :{} | message : {}",id,e.getMessage());
        throw new ServiceUnavailableException("Vehicle service is not have a response");
    }

}
