package insurance.policyService.Consumer;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.policyService.Service.PolicySagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PolicyConsumer {

    Logger log = LoggerFactory.getLogger(PolicyConsumer.class);
    private final PolicySagaService policySagaService;

    public PolicyConsumer(PolicySagaService policySagaService) {
        this.policySagaService = policySagaService;
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_COMPLETED, groupId = "policy-service-final-group-v1")
    public void handlePaymentCompleted(@Payload PaymentCompletedEvent event) {
        log.info("PAYMENT_COMPLETED alındı. Policy ID: {}", event.getPolicyId());
        policySagaService.handlePaymentCompleted(event);
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_FAILED, groupId = "policy-service-final-group-v1")
    public void handlePaymentFailed(@Payload PaymentFailedEvent event){
        log.info("PAYMENT_FAILED  alındı . Policy ID: {}", event.getPolicyId());
        policySagaService.handlePaymentFailed(event);
    }


}