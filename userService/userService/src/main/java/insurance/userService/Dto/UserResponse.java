package insurance.userService.Dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String name,
        String surname,
        String email,
        String phone,
        String status

) {
}
