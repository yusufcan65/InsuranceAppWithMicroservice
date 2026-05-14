package insurance.paymentService.Service;

import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.paymentService.Entity.PolicyProjection;

import java.util.List;
import java.util.UUID;

public interface PolicyProjectionService {

    void createPolicyProjection(PolicyCreatedEvent event);
    void updatePolicyProjection(PolicyCreatedEvent event);
    void deletePolicyProjection(PolicyDeleteEvent event);
    List<PolicyProjection> getAllPoliciesProjections();
    PolicyProjection getPolicyProjectionById(UUID id);


}
