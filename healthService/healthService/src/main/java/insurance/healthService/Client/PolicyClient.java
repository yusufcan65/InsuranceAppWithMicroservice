package insurance.healthService.Client;


import insurance.healthService.Dto.CreateHealthPolicyRequest;
import insurance.healthService.Dto.PolicyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "policyService")
public interface PolicyClient {

    @PostMapping("/v1/policy/internal/feign/create")
    PolicyResponse createPolicy(@RequestBody CreateHealthPolicyRequest request);
}