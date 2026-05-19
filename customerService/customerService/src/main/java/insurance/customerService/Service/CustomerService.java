package insurance.customerService.Service;

import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;
import insurance.customerService.Dto.UpdateCustomerRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse getCustomerForFeign(UUID id);
    CustomerResponse updateCustomer(UUID id,UpdateCustomerRequest request);
    CustomerResponse deleteCustomer(UUID id);

}
