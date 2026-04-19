package insurance.policyService.Repository;

import insurance.policyService.Entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicyRepository extends JpaRepository<Policy, UUID> {
    boolean existsByPolicyNumber(Integer policyNumber);
}
