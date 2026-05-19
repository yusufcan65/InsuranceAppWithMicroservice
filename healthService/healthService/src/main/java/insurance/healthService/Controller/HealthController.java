package insurance.healthService.Controller;

import insurance.healthService.Dto.HealthPolicyResponse;
import insurance.healthService.Dto.HealthRequest;
import insurance.healthService.Dto.HealthResponse;
import insurance.healthService.Service.HealthService;
import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/health-policies")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<HealthPolicyResponse>> createHealthPolicy(@RequestBody HealthRequest healthRequest){
        HealthPolicyResponse healthPolicyResponse = healthService.createHealthPolicy(healthRequest);
        return new ResponseEntity<>(RestResponse.of(healthPolicyResponse), HttpStatus.CREATED);
    }

}
