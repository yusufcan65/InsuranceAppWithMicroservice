package insurance.homeService.Client;

import insurance.homeService.Dto.CreateDaskPolicyRequest;
import insurance.homeService.Dto.PolicyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "policyService")
public interface PolicyClient {

    @PostMapping("/v1/policy/internal/feign/create")
    PolicyResponse createPolicy(@RequestBody CreateDaskPolicyRequest request);
}