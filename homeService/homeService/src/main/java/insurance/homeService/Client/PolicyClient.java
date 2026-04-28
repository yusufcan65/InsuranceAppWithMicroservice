package insurance.homeService.Client;

import insurance.homeService.Dto.CreateDaskPolicyRequest;
import insurance.homeService.Dto.PolicyResponse;
import insurance.insuranceCommon.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "policyService")
public interface PolicyClient {

    @PostMapping("/v1/policy/internal/feign/create")
    RestResponse<PolicyResponse> createPolicy(@RequestBody CreateDaskPolicyRequest request);
}