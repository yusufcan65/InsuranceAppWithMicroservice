package insurance.customerService.Dto;



import java.time.LocalDate;
import java.util.UUID;

public record CustomerRequest(

        String name,
        UUID userId,
        String surname,
        String idNumber,
        LocalDate birthDate,
        String city,
        String district,
        String phoneNumber,
        String email
) {
}
