package insurance.customerService.Dto;

import java.util.UUID;

public record CustomerResponse (
        UUID id,
        String name,
        String surname,
        String phoneNumber,
        Integer customerNumber,
        String idNumber,
        String email
){
}
