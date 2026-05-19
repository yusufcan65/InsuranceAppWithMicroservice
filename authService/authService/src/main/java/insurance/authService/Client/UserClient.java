package insurance.authService.Client;

import insurance.authService.Dto.UserAuthResponse;
import insurance.authService.Exception.ServiceUnavailableException;
import insurance.insuranceCommon.RestResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "userService")
public interface UserClient {

    Logger logger = LoggerFactory.getLogger(UserClient.class);

    @GetMapping("/api/v1/users/internal/auth/{username}")
    @Retry(name = "userRetry")
    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "userFallBack")
    RestResponse<UserAuthResponse> getUserForAuth(@PathVariable String username);

    default RestResponse<UserAuthResponse> userFallBack(String username, Throwable e){

        logger.error("User service is not found with username :{} | message : {}",username,e.getMessage());

        throw new ServiceUnavailableException("User Service not have a response please again 5 minute after");
    }
}