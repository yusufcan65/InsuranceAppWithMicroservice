package insurance.policyService.Consumer;

import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.policyService.Service.PolicyService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PolicyConsumer {

    private final PolicyService policyService;

    public PolicyConsumer(PolicyService policyService) {
        this.policyService = policyService;
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_COMPLETED, groupId = "policy-group-new-era")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        policyService.activePolicy(event.getPolicyId(), event.getPaymentId());

    }

}