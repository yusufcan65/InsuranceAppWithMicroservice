package insurance.healthService.Entity;


import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Health extends BaseEntity {

    private UUID policyId;

    private Integer age;

    private String smokeStatus;
    private String sporStatus;
    private String operationStatus;
    private String chronicDiseaseStatus;
}