package kaskoService.kaskoService.Dto;

import java.util.UUID;

public record KaskoRequest(
        UUID userId,
        UUID customerId,
        UUID carId
) {
}
