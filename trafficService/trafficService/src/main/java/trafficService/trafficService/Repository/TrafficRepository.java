package trafficService.trafficService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trafficService.trafficService.Entity.TrafficPolicyCars;

import java.util.UUID;

public interface TrafficRepository extends JpaRepository<TrafficPolicyCars, UUID> {
}
