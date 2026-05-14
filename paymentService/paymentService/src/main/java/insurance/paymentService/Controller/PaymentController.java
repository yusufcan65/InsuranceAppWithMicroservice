package insurance.paymentService.Controller;

import insurance.insuranceCommon.RestResponse;
import insurance.paymentService.Dto.PaymentDetailResponse;
import insurance.paymentService.Dto.PaymentRequest;
import insurance.paymentService.Entity.PolicyProjection;
import insurance.paymentService.Service.PaymentService;
import insurance.paymentService.Service.PolicyProjectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final PolicyProjectionService policyProjectionService;

    public PaymentController(PaymentService paymentService, PolicyProjectionService policyProjectionService) {
        this.paymentService = paymentService;
        this.policyProjectionService = policyProjectionService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<PaymentDetailResponse>> doPayment(@RequestBody PaymentRequest paymentRequest){
        PaymentDetailResponse paymentDetailResponse = paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(RestResponse.of(paymentDetailResponse), HttpStatus.OK);
    }

    @GetMapping("/policyProjections/policiesforpayment")
    public ResponseEntity<RestResponse<List<PolicyProjection>>> getPoliciesProjections(){
        List<PolicyProjection> policyProjections = policyProjectionService.getAllPoliciesProjections();
        return new ResponseEntity<>(RestResponse.of(policyProjections),HttpStatus.OK);
    }
}
