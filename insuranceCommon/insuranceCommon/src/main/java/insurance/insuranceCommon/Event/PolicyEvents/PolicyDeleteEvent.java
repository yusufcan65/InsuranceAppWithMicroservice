package insurance.insuranceCommon.Event.PolicyEvents;

import java.io.Serializable;
import java.util.UUID;

public class PolicyDeleteEvent implements Serializable {
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
