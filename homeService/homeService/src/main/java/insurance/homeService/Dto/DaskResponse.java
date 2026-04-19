package insurance.homeService.Dto;

public record DaskResponse(
        HomeResponse homeResponse,
        PolicyResponse policyResponse,
        CustomerResponse customerResponse,
        UserResponse userResponse
) {
}
