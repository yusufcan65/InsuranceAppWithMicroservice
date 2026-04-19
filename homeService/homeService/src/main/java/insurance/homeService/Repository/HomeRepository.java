package insurance.homeService.Repository;

import insurance.homeService.Entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomeRepository extends JpaRepository<Home, UUID> {
}
