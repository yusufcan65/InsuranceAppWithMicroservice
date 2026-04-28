package insurance.policyService.Service;

import insurance.policyService.Dto.PolicyRequest;
import insurance.policyService.Dto.PolicyResponse;
import insurance.policyService.Dto.UpdatePolicyRequest;

import java.util.List;
import java.util.UUID;

public interface PolicyService {

    PolicyResponse CreatePolicy(PolicyRequest request);

    PolicyResponse updatePolicy(UpdatePolicyRequest updateRequest);

    PolicyResponse deletePolicy(UUID policyId);

    List<PolicyResponse> getAll();
    PolicyResponse getPolicyById(UUID policyId);

    PolicyResponse getPolicyForPayment(UUID policyId);

    PolicyResponse activePolicy(UUID policyId, UUID paymentId);



}
