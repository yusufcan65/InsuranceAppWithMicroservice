package insurance.policyService.Dto;

import java.util.UUID;

public record UpdatePolicyRequest(

        UUID policyId,
        Double prim

) {
}
