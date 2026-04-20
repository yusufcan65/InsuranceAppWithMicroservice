package insurance.vehicleService.Repository;

import insurance.vehicleService.Entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Cars, UUID> {
}
