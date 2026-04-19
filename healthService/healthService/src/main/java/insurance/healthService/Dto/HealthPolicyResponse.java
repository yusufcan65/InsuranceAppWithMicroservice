package insurance.healthService.Dto;

public record HealthPolicyResponse(
        HealthResponse healthResponse,
        CustomerResponse customerResponse,
        UserResponse userResponse,
        PolicyResponse policyResponse
) {
}
