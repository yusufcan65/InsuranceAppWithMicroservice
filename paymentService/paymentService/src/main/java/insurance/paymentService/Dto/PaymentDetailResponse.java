package insurance.paymentService.Dto;

public record PaymentDetailResponse(
        PaymentResponse paymentResponse,
        PolicyResponse policyResponse
) {
}
