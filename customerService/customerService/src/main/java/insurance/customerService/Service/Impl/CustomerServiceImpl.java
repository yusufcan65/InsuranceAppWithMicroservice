package insurance.customerService.Service.Impl;

import insurance.customerService.Client.UserClient;
import insurance.customerService.Dto.CustomerRequest;
import insurance.customerService.Dto.CustomerResponse;
import insurance.customerService.Dto.UserDTO;
import insurance.customerService.Dto.CustomerPolicyResponse;
import insurance.customerService.Entity.Customer;
import insurance.customerService.Repository.CustomerRepository;
import insurance.customerService.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserClient userClient;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserClient userClient) {
        this.customerRepository = customerRepository;
        this.userClient = userClient;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

        UserDTO user = userClient.getByUserId(customerRequest.userId());

        if(customerRepository.findByIdNumber(customerRequest.idNumber()).isPresent()){
            throw new RuntimeException("customer already exists");
        }

        if(user == null){
            throw new RuntimeException("User Not Found");
        }
        Customer customer = new Customer();
        customer.setCustomerNumber(generateUniqueCustomerNumber());
        customer.setCity(customerRequest.city());
        customer.setBirthDate(customerRequest.birthDate());
        customer.setName(customerRequest.name());
        customer.setEmail(customerRequest.email());
        customer.setDistrict(customerRequest.district());
        customer.setIdNumber(customerRequest.idNumber());
        customer.setUserId(user.userId());
        customer.setPhoneNumber(customerRequest.phoneNumber());
        customer.setSurname(customerRequest.surname());

        Customer toSave = customerRepository.save(customer);


        return toResponse(toSave);

    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return toResponseList(customers);
    }

    private CustomerResponse getById(UUID id){
        Customer customer =  customerRepository.findById(id).orElseThrow(()-> new RuntimeException( "customer not found by Id "+ id));
        return toResponse(customer);
    }

    @Override
    public CustomerPolicyResponse getCustomerForPolicies(UUID id){
        CustomerResponse customerResponse = getById(id);
        CustomerPolicyResponse customerPolicyResponse = new CustomerPolicyResponse(
                customerResponse.id(),
                customerResponse.customerNumber(),
                customerResponse.name(),
                customerResponse.surname(),
                customerResponse.birthDate()
        );
        return customerPolicyResponse;
    }

    private Integer generateUniqueCustomerNumber() {
        Random random = new Random();
        int customerNumber;
        boolean exists;

        do {
            customerNumber = 10000000 + random.nextInt(90000000); // 8 haneli rastgele sayı oluştur
            exists = customerRepository.existsByCustomerNumber(customerNumber); // Mevcut olup olmadığını kontrol et
        } while (exists);

        return customerNumber;
    }

    private CustomerResponse toResponse(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getPhoneNumber(),
                customer.getCustomerNumber(),
                customer.getIdNumber(),
                customer.getEmail(),
                customer.getBirthDate()

        );
        return customerResponse;
    }
    private List<CustomerResponse> toResponseList(List<Customer> customers){
        List<CustomerResponse> customerResponses = customers.stream().
                map(this::toResponse).collect(Collectors.toList());
        return customerResponses;
    }
}
