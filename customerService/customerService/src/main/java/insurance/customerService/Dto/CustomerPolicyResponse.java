package insurance.customerService.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerPolicyResponse(
        UUID id,
        Integer customerNumber,
        String name,
        String surname,
        LocalDate birthDate
) {
}
