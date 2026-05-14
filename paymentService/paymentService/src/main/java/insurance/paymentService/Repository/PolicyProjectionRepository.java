package insurance.paymentService.Repository;

import insurance.paymentService.Entity.PolicyProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicyProjectionRepository extends JpaRepository<PolicyProjection, UUID> {}