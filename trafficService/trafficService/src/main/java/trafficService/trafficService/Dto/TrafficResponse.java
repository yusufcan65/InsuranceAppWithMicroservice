package trafficService.trafficService.Dto;

import io.swagger.v3.oas.models.media.UUIDSchema;

import java.util.UUID;

public record TrafficResponse(
        UUID id,
        UUID policyId,
        UUID customerId,
        UUID carId
) {
}
