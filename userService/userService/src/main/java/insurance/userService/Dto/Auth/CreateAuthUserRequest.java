package insurance.userService.Dto.Auth;

import java.util.Set;

public record CreateAuthUserRequest(
        String username,
        String password
) {
}
