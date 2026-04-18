package insurance.customerService.Repository;

import insurance.customerService.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
     Boolean existsByCustomerNumber(Integer customerNumber);

    Optional<Customer> findByIdNumber(String s);
}
