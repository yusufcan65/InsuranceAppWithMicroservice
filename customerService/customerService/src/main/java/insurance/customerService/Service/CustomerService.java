package insurance.customerService.Service;

import insurance.customerService.Dto.CustomerPolicyResponse;
import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomer();
    CustomerPolicyResponse getCustomerForPolicies(UUID id);

}
