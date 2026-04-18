package insurance.customerService.Controller;

import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;
import insurance.customerService.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        List<CustomerResponse> customerResponses = customerService.getAllCustomer();
        return new ResponseEntity<>(customerResponses,HttpStatus.OK);
    }
}
