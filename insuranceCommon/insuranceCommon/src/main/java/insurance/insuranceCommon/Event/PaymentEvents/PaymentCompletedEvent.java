package insurance.insuranceCommon.Event.PaymentEvents;

import com.fasterxml.jackson.annotation.JsonProperty;
import insurance.insuranceCommon.Event.AbstractBaseEvent;

import java.io.Serializable;
import java.util.UUID;

public class PaymentCompletedEvent extends AbstractBaseEvent {

    @JsonProperty("policyId")
    private UUID policyId;

    @JsonProperty("paymentId")
    private UUID paymentId;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("policyNumber")
    private Integer policyNumber;


    public PaymentCompletedEvent() {
    }

    public PaymentCompletedEvent(UUID policyId, UUID paymentId, Double amount, Integer policyNumber) {
        this.policyId = policyId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.policyNumber = policyNumber;
    }


    public UUID getPolicyId() { return policyId; }
    public void setPolicyId(UUID policyId) { this.policyId = policyId; }

    public UUID getPaymentId() { return paymentId; }
    public void setPaymentId(UUID paymentId) { this.paymentId = paymentId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Integer getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(Integer policyNumber) { this.policyNumber = policyNumber; }
}
