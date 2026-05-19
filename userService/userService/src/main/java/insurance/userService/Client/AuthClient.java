package insurance.userService.Client;

import insurance.insuranceCommon.RestResponse;
import insurance.userService.Dto.Auth.CreateAuthUserRequest;
import insurance.userService.Dto.UserAuthResponse;
import insurance.userService.Exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authService")
public interface AuthClient {

    Logger logger = LoggerFactory.getLogger(AuthClient.class);
    @PostMapping("/api/v1/auth/internal/authUser")
    @Retry(name = "authRetry")
    @CircuitBreaker(name = "authServiceCB", fallbackMethod = "authFallBack")
    RestResponse<UserAuthResponse> createAuthUser(@RequestBody CreateAuthUserRequest request);

    default RestResponse<UserAuthResponse> authFallBack(CreateAuthUserRequest request, Throwable e){
        logger.error("Auth service hata verdi ve User kaydı gerçekleşmedi :",request,e.getMessage());
        throw new ServiceUnavailableException("Auth service meşgul daha sonra tekrar deneyiniz.");
    }
}