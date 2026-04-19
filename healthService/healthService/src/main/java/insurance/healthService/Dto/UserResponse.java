package insurance.healthService.Dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String name,
        String surname
) {
}
