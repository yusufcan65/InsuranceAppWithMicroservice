package insurance.policyService.Service;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;

public interface PolicySagaService {
    void handlePaymentCompleted(PaymentCompletedEvent event);
    void handlePaymentFailed(PaymentFailedEvent event);
}
