package insurance.authService.Dto;

public record CreateAuthUserRequest(
        String username,
        String password
) {
}
