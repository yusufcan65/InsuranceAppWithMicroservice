package insurance.insuranceCommon.Event.PolicyEvents;

import insurance.insuranceCommon.Event.AbstractBaseEvent;

import java.util.UUID;

public class PolicyDeleteEvent extends AbstractBaseEvent {
    private UUID policyId;

    public PolicyDeleteEvent() {
    }

    public PolicyDeleteEvent(UUID policyId) {
        this.policyId = policyId;
    }

    public UUID getPolicyId() {
        return policyId;
    }

    public void setPolicyId(UUID policyId) {
        this.policyId = policyId;
    }
}
