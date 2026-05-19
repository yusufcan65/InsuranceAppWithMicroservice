package insurance.trafficService.Client;


import insurance.insuranceCommon.RestResponse;
import insurance.trafficService.Dto.PolicyResponse;
import insurance.trafficService.Exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import insurance.trafficService.Dto.CreateTrafficPolicyRequest;


@FeignClient(name = "policyService")
public interface PolicyClient {

    Logger logger = LoggerFactory.getLogger(PolicyClient.class);

    @PostMapping("/api/v1/policies/internal/create")
    @Retry(name = "policyRetry")
    @CircuitBreaker(name = "policyServiceCB", fallbackMethod = "policyFallBack")
    RestResponse<PolicyResponse> createPolicy(@RequestBody CreateTrafficPolicyRequest request);

    default RestResponse<PolicyResponse> policyFallBack(CreateTrafficPolicyRequest request, Throwable e){
        logger.error("policy service hata verdi ve poliçe kaydı gerçekleşmedi :",e.getMessage());
        throw new ServiceUnavailableException("Policy service meşgul daha sonra tekrar deneyiniz.");
    }
}