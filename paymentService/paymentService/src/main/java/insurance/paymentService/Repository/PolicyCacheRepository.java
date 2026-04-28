package insurance.paymentService.Repository;

import insurance.paymentService.Entity.PolicyCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicyCacheRepository extends JpaRepository<PolicyCache, UUID> {}