package insurance.customerService.Dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record CustomerResponse (
        UUID id,
        String name,
        String surname,
        String phoneNumber,
        Integer customerNumber,
        String idNumber,
        String email,
        LocalDate birthDate
) implements Serializable {
}
