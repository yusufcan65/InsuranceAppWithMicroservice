package insurance.customerService.Dto;

import java.util.UUID;

public record CustomerPolicyResponse(
        UUID id,
        Integer customerNumber,
        String name,
        String surname
) {
}
