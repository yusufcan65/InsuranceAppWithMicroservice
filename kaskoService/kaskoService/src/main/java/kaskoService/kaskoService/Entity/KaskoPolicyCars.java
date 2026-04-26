package kaskoService.kaskoService.Entity;

import insurance.insuranceCommon.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "kasko-policied-cars-table")
public class KaskoPolicyCars extends BaseEntity {

    private UUID policyId;
    private UUID customerId;
    private UUID carId;

    public UUID getPolicyId() {
        return policyId;
    }

    public void setPolicyId(UUID policyId) {
        this.policyId = policyId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }
}
