package trafficService.trafficService.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerResponse(

        UUID id,
        String name,
        String surname,
        Integer customerNumber,
        LocalDate birthDate
) {
}
