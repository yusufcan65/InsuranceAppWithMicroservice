package insurance.healthService.Repository;

import insurance.healthService.Entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HealthRepository extends JpaRepository<Health, UUID> {
}
