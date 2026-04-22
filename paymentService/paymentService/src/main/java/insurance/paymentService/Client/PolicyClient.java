package insurance.paymentService.Client;

import insurance.paymentService.Dto.PolicyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "policyService")
public interface PolicyClient {

    @GetMapping("/v1/policy/internal/feign/{id}")
    PolicyResponse getPolicyForFeign(@PathVariable UUID id);

    @PutMapping("/v1/policy/internal/active/feign/{policyId}/{paymentId}")
    PolicyResponse activePolicy(@PathVariable UUID policyId, @PathVariable UUID paymentId);
}
