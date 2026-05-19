package insurance.userService.Dto;

public record UpdateUserRequest (
        String name,
        String surname,
        String email,
        String phone
){
}
