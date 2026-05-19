package insurance.customerService.Controller;

import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;
import insurance.customerService.Dto.UpdateCustomerRequest;
import insurance.customerService.Service.CustomerService;
import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<RestResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(RestResponse.of(customerResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerResponse>>> getAllCustomer(){
        List<CustomerResponse> customerResponses = customerService.getAllCustomer();
        return new ResponseEntity<>(RestResponse.of(customerResponses),HttpStatus.OK);


    }

    @GetMapping("/internal/{id}")
    public ResponseEntity<RestResponse<CustomerResponse>> getCustomerForFeign(@PathVariable UUID id){
        CustomerResponse customerResponse = customerService.getCustomerForFeign(id);
        return new ResponseEntity<>(RestResponse.of(customerResponse), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerResponse>> updateCustomer(@PathVariable UUID id, @RequestBody UpdateCustomerRequest request){
        CustomerResponse customerResponse = customerService.updateCustomer(id,request);
        return new ResponseEntity<>(RestResponse.of(customerResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerResponse>> deleteCustomer(@PathVariable UUID id){
        CustomerResponse customerResponse = customerService.deleteCustomer(id);
        return new ResponseEntity<>(RestResponse.of(customerResponse),HttpStatus.OK);
    }

}
