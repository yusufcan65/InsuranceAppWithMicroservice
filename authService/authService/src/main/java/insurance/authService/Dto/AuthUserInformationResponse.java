package insurance.authService.Dto;

import insurance.authService.Entity.Role;

public record AuthUserInformationResponse (
        String username,
        boolean enabled,
        Role role
){
}
