package insurance.paymentService.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        Double amount,
        Integer policyNumber,
        LocalDate paymentDate,
        String cardNumber,
        String cardOwner,
        UUID policyId
) {
}
