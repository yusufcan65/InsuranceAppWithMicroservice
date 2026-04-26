package trafficService.trafficService.Client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import trafficService.trafficService.Dto.UserResponse;
import trafficService.trafficService.Exception.ServiceUnavailableException;

import java.util.UUID;

@FeignClient(name = "userService")  // user-service değil, userService olmalı
public interface UserClient {

    Logger logger = LoggerFactory.getLogger(UserClient.class);

    @GetMapping("/v1/user/internal/feign/{id}")
    @Retry(name = "userRetry")
    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "userFallBack")
    UserResponse getUserForFeign(@PathVariable UUID id);


    default UserResponse userFallBack(UUID id, Throwable e){

        logger.error("User service is not found with id :{} | message : {}",id,e.getMessage());

        throw new ServiceUnavailableException("User Service not have a response");
    }

}