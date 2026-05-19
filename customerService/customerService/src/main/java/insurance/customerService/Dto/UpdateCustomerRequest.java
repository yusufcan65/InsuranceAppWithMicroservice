package insurance.customerService.Dto;

import java.util.UUID;

public record UpdateCustomerRequest(
        String name,
        String surname,
        String phoneNumber,
        String email
) {
}
