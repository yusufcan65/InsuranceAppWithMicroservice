package insurance.paymentService.Controller;

import insurance.paymentService.Dto.PaymentDetailResponse;
import insurance.paymentService.Dto.PaymentRequest;
import insurance.paymentService.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDetailResponse> doPayment(@RequestBody PaymentRequest paymentRequest){
        PaymentDetailResponse paymentDetailResponse = paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(paymentDetailResponse, HttpStatus.OK);
    }
}
