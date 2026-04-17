package insurance.userService.Dto;

import java.util.UUID;

public record UserAuthResponse(
        UUID id,
        String username,
        String status
) {
}
