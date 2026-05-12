package insurance.policyService.Consumer;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.policyService.Service.PolicyService;
import insurance.policyService.Service.Producer.PolicyProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PolicyConsumer {

    Logger log = LoggerFactory.getLogger(PolicyConsumer.class);
    private final PolicyService policyService;
    private final PolicyProducer policyProducer;

    public PolicyConsumer(PolicyService policyService, PolicyProducer policyProducer) {
        this.policyService = policyService;
        this.policyProducer = policyProducer;
    }

  /*  @KafkaListener(topics = KafkaTopics.PAYMENT_COMPLETED, groupId = "policy-group-new-era")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        policyService.activePolicy(event.getPolicyId(), event.getPaymentId());

    }*/

    @KafkaListener(topics = KafkaTopics.PAYMENT_COMPLETED, groupId = "policy-group-new-era")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        log.info("Payment completed eventi alındı: {}", event.getPolicyId());
        try {
            policyService.activePolicy(event.getPolicyId(), event.getPaymentId());

            // Başarılı → POLICY_ACTIVATED eventi gönder
            policyProducer.sendPolicyActivated(
                    new PolicyActivatedEvent(
                            event.getPolicyId(),
                            event.getPaymentId(),
                            event.getPolicyNumber()
                    )
            );

        } catch (Exception e) {
            log.error("Poliçe aktifleştirilemedi: {}", e.getMessage());

            // Başarısız → PAYMENT_FAILED eventi gönder
            policyProducer.sendPaymentFailed(
                    new PaymentFailedEvent(
                            event.getPolicyId(),
                            event.getPaymentId(),
                            e.getMessage()
                    )
            );
        }
    }

}