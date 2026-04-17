package insurance.vehicleService.Entity;

import jakarta.persistence.Entity;

@Entity
public class Cars extends BaseEntity {

    private String brand;
    private String model;

    private Integer modelYear;
    private Double insuranceValue;
}