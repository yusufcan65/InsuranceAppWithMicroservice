package insurance.policyService.Service;

import insurance.policyService.Dto.PolicyRequest;
import insurance.policyService.Dto.PolicyResponse;

import java.util.List;
import java.util.UUID;

public interface PolicyService {

    PolicyResponse CreatePolicy(PolicyRequest request);
    List<PolicyResponse> getAll();
    PolicyResponse getPolicyById(UUID policyId);

}
