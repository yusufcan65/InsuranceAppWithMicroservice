package insurance.healthService.Client;


import insurance.healthService.Dto.CreateHealthPolicyRequest;
import insurance.healthService.Dto.PolicyResponse;
import insurance.healthService.Exception.ServiceUnavailableException;
import insurance.insuranceCommon.RestResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "policyService")
public interface PolicyClient {

    Logger logger = LoggerFactory.getLogger(PolicyClient.class);

    @PostMapping("/api/v1/policies/internal/create")
    @Retry(name = "policyRetry")
    @CircuitBreaker(name = "policyServiceCB", fallbackMethod = "policyFallBack")
    RestResponse<PolicyResponse> createPolicy(@RequestBody CreateHealthPolicyRequest request);

    default RestResponse<PolicyResponse> policyFallBack(CreateHealthPolicyRequest request, Throwable e){
        logger.error("policy service hata verdi ve poliçe kaydı gerçekleşmedi :",e.getMessage());
        throw new ServiceUnavailableException("Policy service meşgul daha sonra tekrar deneyiniz.");
    }
}