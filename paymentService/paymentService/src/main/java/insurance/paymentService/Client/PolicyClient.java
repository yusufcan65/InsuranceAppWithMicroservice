package insurance.paymentService.Client;

/* Kafka eklendi ve burada yer alan PolicyClient yapısı devre dışı bırakıldı
* */

import insurance.insuranceCommon.RestResponse;
import insurance.paymentService.Dto.PolicyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;
/*
@FeignClient(name = "policyService")
public interface PolicyClient {

    @GetMapping("/v1/policy/internal/feign/payment/{id}")
    RestResponse<PolicyResponse> getPolicyForPayment(@PathVariable UUID id);

    // kafka eklendiği için feign client ile atılan post ve put istekleri devre dışı bırakıldı
    @PutMapping("/v1/policy/internal/active/feign/{policyId}/{paymentId}")
    PolicyResponse activePolicy(@PathVariable UUID policyId, @PathVariable UUID paymentId);


}
*/