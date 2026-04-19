package insurance.healthService.Dto;

import java.util.UUID;

public record HealthRequest(
        UUID userId,
        UUID customerId,
        String smokeStatus,
        String sporStatus,
        String operationStatus,
        String chronicDiseaseStatus
) {
}
