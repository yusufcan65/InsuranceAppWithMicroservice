package insurance.paymentService.Service;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.paymentService.Dto.PaymentDetailResponse;
import insurance.paymentService.Dto.PaymentRequest;

public interface PaymentService {

    PaymentDetailResponse doPayment(PaymentRequest request);
    void confirmPayment(PolicyActivatedEvent event);
    void failPayment(PaymentFailedEvent event);

}
