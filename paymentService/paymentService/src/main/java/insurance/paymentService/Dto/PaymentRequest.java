package insurance.paymentService.Dto;

import java.util.UUID;

public record PaymentRequest (
        UUID policyId,
        String cardNumber,
        String cardOwner,
        String expiryDate,
        String cvv
){
}
