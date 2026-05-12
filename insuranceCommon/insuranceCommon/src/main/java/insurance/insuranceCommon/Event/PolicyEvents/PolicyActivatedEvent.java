package insurance.insuranceCommon.Event.PolicyEvents;

import insurance.insuranceCommon.Event.AbstractBaseEvent;

import java.util.UUID;

public class PolicyActivatedEvent extends AbstractBaseEvent {
    private UUID policyId;
    private UUID paymentId;
    private Integer policyNumber;

    public PolicyActivatedEvent() {
    }

    public PolicyActivatedEvent(UUID policyId, UUID paymentId, Integer policyNumber) {
        this.policyId = policyId;
        this.paymentId = paymentId;
        this.policyNumber = policyNumber;
    }

    public UUID getPolicyId() {
        return policyId;
    }

    public void setPolicyId(UUID policyId) {
        this.policyId = policyId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }
}