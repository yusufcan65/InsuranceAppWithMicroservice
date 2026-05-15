package insurance.customerService.Controller;

import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;
import insurance.customerService.Service.CustomerService;
import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/create")
    public ResponseEntity<RestResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(RestResponse.of(customerResponse), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<RestResponse<List<CustomerResponse>>> getAllCustomer(){
        List<CustomerResponse> customerResponses = customerService.getAllCustomer();
        return new ResponseEntity<>(RestResponse.of(customerResponses),HttpStatus.OK);


    }

    @GetMapping("/internal/feign/{id}")
    public ResponseEntity<RestResponse<CustomerResponse>> getCustomerForFeign(@PathVariable UUID id){
        CustomerResponse customerResponse = customerService.getCustomerForFeign(id);
        return new ResponseEntity<>(RestResponse.of(customerResponse), HttpStatus.OK);
    }
}
