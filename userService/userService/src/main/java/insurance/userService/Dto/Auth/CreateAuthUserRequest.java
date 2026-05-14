package insurance.userService.Dto.Auth;

public record CreateAuthUserRequest(
        String username,
        String password
) {
}
