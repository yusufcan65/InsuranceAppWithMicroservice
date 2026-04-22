package insurance.policyService.Controller;

import insurance.policyService.Dto.PolicyRequest;
import insurance.policyService.Dto.PolicyResponse;
import insurance.policyService.Service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/internal/feign/create")
    public ResponseEntity<PolicyResponse> createPolicy(@RequestBody PolicyRequest policyRequest){
        PolicyResponse policyResponse = policyService.CreatePolicy(policyRequest);
        return new ResponseEntity<>(policyResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PolicyResponse>> getAllPolicies(){
        List<PolicyResponse> policyResponses = policyService.getAll();
        return new ResponseEntity<>(policyResponses, HttpStatus.OK);
    }

    @GetMapping("/internal/feign/{id}")
    public ResponseEntity<PolicyResponse> getPolicyForFeign(@PathVariable UUID id){
        PolicyResponse policyResponse = policyService.getPolicyById(id);
        return new ResponseEntity<>(policyResponse, HttpStatus.OK);
    }

    @PutMapping("/internal/active/feign/{policyId}/{paymentId}")
    public ResponseEntity<PolicyResponse> activePolicy(@PathVariable UUID policyId, @PathVariable UUID paymentId){
        PolicyResponse policyResponse = policyService.activePolicy(policyId,paymentId);
        return new ResponseEntity<>(policyResponse, HttpStatus.OK);
    }
}
