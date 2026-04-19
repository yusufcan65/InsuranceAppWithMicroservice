package insurance.userService.Dto;

import java.util.UUID;

public record UserFeignResponse(
        UUID id,
        String username,
        String name,
        String surname
) {
}
