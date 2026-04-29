package insurance.insuranceCommon.Event.PolicyEvents;

import insurance.insuranceCommon.Event.AbstractBaseEvent;

import java.io.Serializable;
import java.util.UUID;

public class PolicyCreatedEvent  extends AbstractBaseEvent {
    private UUID id;
    private Double prim;
    private Integer policyNumber;


    public PolicyCreatedEvent() {}


    public PolicyCreatedEvent(UUID id, Double prim, Integer policyNumber) {
        this.id = id;
        this.prim = prim;
        this.policyNumber = policyNumber;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Double getPrim() { return prim; }
    public void setPrim(Double prim) { this.prim = prim; }
    public Integer getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(Integer policyNumber) { this.policyNumber = policyNumber; }
}