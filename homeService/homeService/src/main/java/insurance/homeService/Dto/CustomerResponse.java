package insurance.homeService.Dto;

import java.util.UUID;

public record CustomerResponse(

        UUID id,
        String name,
        String surname,
        Integer customerNumber
) {
}
