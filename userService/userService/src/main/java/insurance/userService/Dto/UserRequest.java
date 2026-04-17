package insurance.userService.Dto;

public record UserRequest(
        String username,
        String password,
        String name,
        String surname,
        String email,
        String phone
) {
}
