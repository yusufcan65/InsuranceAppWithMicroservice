package insurance.homeService.Entity;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Home extends BaseEntity {

    private UUID policyId;

    private String addressCode;
    private Double squareMeter;
    private int floorNumber;

    private String buildStyle;
    private int numberBuildFloor;

    private String damageState;
    private int buildingAge;
}