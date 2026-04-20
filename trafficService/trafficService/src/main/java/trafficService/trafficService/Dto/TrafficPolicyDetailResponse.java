package trafficService.trafficService.Dto;

public record TrafficPolicyDetailResponse(
        PolicyResponse policyResponse,
        CustomerResponse customerResponse,
        UserResponse userResponse,
        TrafficResponse trafficReponse
) {
}
