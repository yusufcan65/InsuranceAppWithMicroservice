package insurance.policyService.Controller;

import insurance.insuranceCommon.RestResponse;
import insurance.policyService.Dto.PolicyRequest;
import insurance.policyService.Dto.PolicyResponse;
import insurance.policyService.Dto.UpdatePolicyRequest;
import insurance.policyService.Service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/internal/create")
    public ResponseEntity<RestResponse<PolicyResponse>> createPolicy(@RequestBody PolicyRequest policyRequest){
        PolicyResponse policyResponse = policyService.CreatePolicy(policyRequest);
        return new ResponseEntity<>(RestResponse.of(policyResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<PolicyResponse>>> getAllPolicies(){
        List<PolicyResponse> policyResponses = policyService.getAll();
        return new ResponseEntity<>(RestResponse.of(policyResponses), HttpStatus.OK);
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<RestResponse<PolicyResponse>> updatePolicy(@PathVariable UUID id, @RequestBody UpdatePolicyRequest request){
        PolicyResponse policyResponse = policyService.updatePolicy(id,request);
        return new ResponseEntity<>(RestResponse.of(policyResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<PolicyResponse>> deletePolicy(@PathVariable UUID id){
        PolicyResponse policyResponse = policyService.deletePolicy(id);
        return new ResponseEntity<>(RestResponse.of(policyResponse), HttpStatus.OK);
    }

    // kafka eklendiği için bu apiler kullanımı kapatıldı
    /*@GetMapping("/internal/{id}")
    public ResponseEntity<RestResponse<PolicyResponse>> getPolicyForFeign(@PathVariable UUID id){
        PolicyResponse policyResponse = policyService.getPolicyById(id);
        return new ResponseEntity<>(RestResponse.of(policyResponse), HttpStatus.OK);
    }*/
    /*@GetMapping("/internal/payment/{id}")
    public ResponseEntity<RestResponse<PolicyResponse>> getPolicyForPayment(@PathVariable UUID id){
        PolicyResponse policyResponse = policyService.getPolicyForPayment(id);
        return new ResponseEntity<>(RestResponse.of(policyResponse), HttpStatus.OK);
    }*/

   /* @PutMapping("/internal/active/policy/{policyId}/{paymentId}")
    public ResponseEntity<RestResponse<PolicyResponse>> activePolicy(@PathVariable UUID policyId, @PathVariable UUID paymentId){
        PolicyResponse policyResponse = policyService.activePolicy(policyId,paymentId);
        return new ResponseEntity<>(RestResponse.of(policyResponse), HttpStatus.OK);
    }*/


}
