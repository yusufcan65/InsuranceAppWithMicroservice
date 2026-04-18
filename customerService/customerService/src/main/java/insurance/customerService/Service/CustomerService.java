package insurance.customerService.Service;

import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomer();

}
