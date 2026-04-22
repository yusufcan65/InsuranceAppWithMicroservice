package insurance.paymentService.Service;

import insurance.paymentService.Dto.PaymentDetailResponse;
import insurance.paymentService.Dto.PaymentRequest;

public interface PaymentService {

    PaymentDetailResponse doPayment(PaymentRequest request);

}
