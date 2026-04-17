package insurance.authService.Dto;

import insurance.authService.Entity.Role;

import java.util.Set;

public record CreateAuthUserRequest(
        String username,
        String password,
        Role roles
) {
}
