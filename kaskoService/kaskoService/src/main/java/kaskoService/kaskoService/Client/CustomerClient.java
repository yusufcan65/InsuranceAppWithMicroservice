package kaskoService.kaskoService.Client;


import insurance.insuranceCommon.RestResponse;
import kaskoService.kaskoService.Dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.UUID;

@FeignClient(name = "customerService")
public interface CustomerClient {

    @GetMapping("/v1/customer/internal/feign/{id}")
    RestResponse<CustomerResponse> getCustomerForFeign(@PathVariable UUID id);
}