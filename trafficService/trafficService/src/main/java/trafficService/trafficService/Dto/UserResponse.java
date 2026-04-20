package trafficService.trafficService.Dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String name,
        String surname
) {
}
