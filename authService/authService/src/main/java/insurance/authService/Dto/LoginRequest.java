package insurance.authService.Dto;

public record LoginRequest(
        String username,
        String password
) {
}
