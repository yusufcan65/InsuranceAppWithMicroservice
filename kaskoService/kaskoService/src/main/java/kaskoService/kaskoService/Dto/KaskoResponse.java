package kaskoService.kaskoService.Dto;

import java.util.UUID;

public record KaskoResponse(
        UUID id,
        UUID policyId,
        UUID customerId,
        UUID carId
) {
}
