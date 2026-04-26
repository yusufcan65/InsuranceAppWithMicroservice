package insurance.healthService.Entity;


import insurance.insuranceCommon.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "health-table")
public class Health extends BaseEntity {

    private UUID policyId;
    private UUID customerId;
    private Integer age;
    private String smokeStatus;
    private String sporStatus;
    private String operationStatus;
    private String chronicDiseaseStatus;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSmokeStatus() {
        return smokeStatus;
    }

    public void setSmokeStatus(String smokeStatus) {
        this.smokeStatus = smokeStatus;
    }

    public String getSporStatus() {
        return sporStatus;
    }

    public void setSporStatus(String sporStatus) {
        this.sporStatus = sporStatus;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getChronicDiseaseStatus() {
        return chronicDiseaseStatus;
    }

    public void setChronicDiseaseStatus(String chronicDiseaseStatus) {
        this.chronicDiseaseStatus = chronicDiseaseStatus;
    }
}