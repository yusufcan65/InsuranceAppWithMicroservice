package insurance.authService.Dto;

import java.util.UUID;

public record UserAuthResponse(
        UUID id,
        String username
) {
}
