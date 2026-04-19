package insurance.healthService.Controller;

import insurance.healthService.Dto.HealthPolicyResponse;
import insurance.healthService.Dto.HealthRequest;
import insurance.healthService.Dto.HealthResponse;
import insurance.healthService.Service.HealthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @PostMapping("/create")
    public ResponseEntity<HealthPolicyResponse> createHealthPolicy(@RequestBody HealthRequest healthRequest){
        HealthPolicyResponse healthPolicyResponse = healthService.createHealthPolicy(healthRequest);
        return new ResponseEntity<>(healthPolicyResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<HealthResponse>> getAllHealths(){
        List<HealthResponse> healthResponses = healthService.getAllHealths();
        return new ResponseEntity<>(healthResponses,HttpStatus.OK);
    }
}
