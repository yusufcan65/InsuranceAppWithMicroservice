package insurance.kaskoService.Repository;

import insurance.kaskoService.Entity.KaskoPolicyCars;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface KaskoRepository extends JpaRepository<KaskoPolicyCars, UUID> {
}
