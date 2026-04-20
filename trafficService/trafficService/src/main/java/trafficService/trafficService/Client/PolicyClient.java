package trafficService.trafficService.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import trafficService.trafficService.Dto.CreateTrafficPolicyRequest;
import trafficService.trafficService.Dto.PolicyResponse;

@FeignClient(name = "policyService")
public interface PolicyClient {

    @PostMapping("/v1/policy/internal/feign/create")
    PolicyResponse createPolicy(@RequestBody CreateTrafficPolicyRequest request);
}