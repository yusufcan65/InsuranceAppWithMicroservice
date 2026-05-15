package insurance.trafficService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import insurance.trafficService.Entity.TrafficPolicyCars;

import java.util.UUID;

public interface TrafficRepository extends JpaRepository<TrafficPolicyCars, UUID> {
}
