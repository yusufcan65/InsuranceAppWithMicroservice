package insurance.healthService.Service;

import insurance.healthService.Dto.HealthPolicyResponse;
import insurance.healthService.Dto.HealthRequest;
import insurance.healthService.Dto.HealthResponse;

import java.util.List;

public interface HealthService {

    HealthPolicyResponse createHealthPolicy(HealthRequest request);
    List<HealthResponse> getAllHealths();
}
