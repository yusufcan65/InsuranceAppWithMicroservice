package insurance.paymentService.Dto;

import java.util.UUID;

public record PolicyResponse(
        UUID id,
        Integer policyNumber,
        Double prim,
        UUID paymentId
) {
}
