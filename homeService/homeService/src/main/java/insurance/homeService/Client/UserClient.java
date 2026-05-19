package insurance.homeService.Client;


import insurance.homeService.Dto.UserResponse;
import insurance.homeService.Exception.ServiceUnavailableException;
import insurance.insuranceCommon.RestResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "userService")
public interface UserClient {

    Logger logger = LoggerFactory.getLogger(UserClient.class);

    @GetMapping("/api/v1/users/internal/{id}")
    @Retry(name = "userRetry")
    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "userFallBack")
    RestResponse<UserResponse> getUserForFeign(@PathVariable UUID id);


    default RestResponse<UserResponse> userFallBack(UUID id, Throwable e){

        logger.error("User service is not found with id :{} | message : {}",id,e.getMessage());

        throw new ServiceUnavailableException("User Service not have a response please again 5 minute after");
    }
}