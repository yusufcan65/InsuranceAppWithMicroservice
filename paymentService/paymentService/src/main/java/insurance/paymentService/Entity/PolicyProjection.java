package insurance.paymentService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "policy_projection_table")
public class PolicyProjection {
    @Id
    private UUID id;
    private Double prim;
    private Integer policyNumber;

    public PolicyProjection(UUID id, Double prim, Integer policyNumber) {
        this.id = id;
        this.prim = prim;
        this.policyNumber = policyNumber;
    }

    public PolicyProjection() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getPrim() {
        return prim;
    }

    public void setPrim(Double prim) {
        this.prim = prim;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }
}