package trafficService.trafficService.Dto;

import java.util.UUID;

public record TrafficRequest(
        UUID userId,
        UUID customerId,
        UUID carId
) {
}
