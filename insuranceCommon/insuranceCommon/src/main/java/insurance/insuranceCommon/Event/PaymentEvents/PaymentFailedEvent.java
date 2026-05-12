package insurance.insuranceCommon.Event.PaymentEvents;

import insurance.insuranceCommon.Event.AbstractBaseEvent;

import java.util.UUID;

public class PaymentFailedEvent extends AbstractBaseEvent {
    private UUID policyId;
    private UUID paymentId;
    private String reason;

    public PaymentFailedEvent() {
    }

    public PaymentFailedEvent(UUID policyId, UUID paymentId, String reason) {
        this.policyId = policyId;
        this.paymentId = paymentId;
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
