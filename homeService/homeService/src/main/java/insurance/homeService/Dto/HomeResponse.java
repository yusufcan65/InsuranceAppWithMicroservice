package insurance.homeService.Dto;

import java.util.UUID;

public record HomeResponse(
        UUID policyId,
        UUID customerId,
        UUID id,
        String addressCode,
        Double squareMeter,
        int floorNumber,
        String buildStyle,
        int numberBuildFloor,
        String damageState,
        int buildingAge
) {
}
