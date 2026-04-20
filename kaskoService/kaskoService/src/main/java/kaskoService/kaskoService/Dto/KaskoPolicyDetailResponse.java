package kaskoService.kaskoService.Dto;

public record KaskoPolicyDetailResponse(
        PolicyResponse policyResponse,
        CustomerResponse customerResponse,
        UserResponse userResponse,
        KaskoResponse kaskoResponse
) {
}
