package trafficService.trafficService.Client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import trafficService.trafficService.Dto.CreateTrafficPolicyRequest;
import trafficService.trafficService.Dto.PolicyResponse;
import trafficService.trafficService.Exception.ServiceUnavailableException;


@FeignClient(name = "policyService")
public interface PolicyClient {

    Logger logger = LoggerFactory.getLogger(PolicyClient.class);

    @PostMapping("/v1/policy/internal/feign/create")
    @Retry(name = "policyRetry")
    @CircuitBreaker(name = "policyServiceCB", fallbackMethod = "policyFallBack")
    PolicyResponse createPolicy(@RequestBody CreateTrafficPolicyRequest request);

    default PolicyResponse policyFallBack(CreateTrafficPolicyRequest request, Throwable e){

        logger.error("policy service hata verdi ve poliçe kaydı gerçekleşmedi :",e.getMessage());

        throw new ServiceUnavailableException("Policy service meşgul daha sonra tekrar deneyiniz.");
    }
}