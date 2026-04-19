package insurance.healthService.Dto;

import java.util.UUID;

public record HealthResponse(
        UUID id,
        Integer age,
        String smokeStatus,
        String sporStatus,
        String operationStatus,
        String chronicDiseaseStatus,
        UUID policyId,
        UUID customerId

) {
}
