package insurance.vehicleService.Entity;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class PoliciedCars extends BaseEntity {

    private UUID policyId;

    private UUID carId;

    private String plateCityCode;
    private String plateCode;

    private String engineNumber;
    private String frameNumber;
}