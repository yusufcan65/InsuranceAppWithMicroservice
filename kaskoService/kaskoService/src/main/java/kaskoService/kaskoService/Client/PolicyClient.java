package kaskoService.kaskoService.Client;


import kaskoService.kaskoService.Dto.CreateKaskoPolicyRequest;
import kaskoService.kaskoService.Dto.PolicyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "policyService")
public interface PolicyClient {

    @PostMapping("/v1/policy/internal/feign/create")
    PolicyResponse createPolicy(@RequestBody CreateKaskoPolicyRequest request);
}