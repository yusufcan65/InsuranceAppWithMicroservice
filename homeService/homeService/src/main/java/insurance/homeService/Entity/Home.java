package insurance.homeService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "home-table")
public class Home extends BaseEntity {

    private UUID policyId;

    private UUID customerId;
    private String addressCode;
    private Double squareMeter;
    private int floorNumber;

    private String buildStyle;
    private int numberBuildFloor;

    private String damageState;
    private int buildingAge;

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

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public Double getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(Double squareMeter) {
        this.squareMeter = squareMeter;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getBuildStyle() {
        return buildStyle;
    }

    public void setBuildStyle(String buildStyle) {
        this.buildStyle = buildStyle;
    }

    public int getNumberBuildFloor() {
        return numberBuildFloor;
    }

    public void setNumberBuildFloor(int numberBuildFloor) {
        this.numberBuildFloor = numberBuildFloor;
    }

    public String getDamageState() {
        return damageState;
    }

    public void setDamageState(String damageState) {
        this.damageState = damageState;
    }

    public int getBuildingAge() {
        return buildingAge;
    }

    public void setBuildingAge(int buildingAge) {
        this.buildingAge = buildingAge;
    }
}