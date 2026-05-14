package insurance.policyService.Producer;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PolicyProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    Logger log = LoggerFactory.getLogger(PolicyProducer.class);

    public PolicyProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPolicyCreated(PolicyCreatedEvent event) {
        log.info("POLICY_CREATED gönderiliyor. Policy ID: {}", event.getId());
        kafkaTemplate.send(KafkaTopics.POLICY_CREATED, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("POLICY_CREATED gönderilemedi. Policy ID: {}, Hata: {}",
                                event.getId(), ex.getMessage());
                    } else {
                        log.info("POLICY_CREATED başarıyla gönderildi. Policy ID: {}", event.getId());
                    }
                });
    }

    public void sendPolicyUpdated(PolicyCreatedEvent event) {
        log.info("POLICY_UPDATED gönderiliyor. Policy ID: {}", event.getId());
        kafkaTemplate.send(KafkaTopics.POLICY_UPDATED, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("POLICY_UPDATED gönderilemedi. Policy ID: {}, Hata: {}",
                                event.getId(), ex.getMessage());
                    } else {
                        log.info("POLICY_UPDATED başarıyla gönderildi. Policy ID: {}", event.getId());
                    }
                });
    }

    public void sendPolicyDeleted(PolicyDeleteEvent event) {
        log.info("POLICY_DELETED gönderiliyor. Policy ID: {}", event.getPolicyId());
        kafkaTemplate.send(KafkaTopics.POLICY_DELETED, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("POLICY_DELETED gönderilemedi. Policy ID: {}, Hata: {}",
                                event.getPolicyId(), ex.getMessage());
                    } else {
                        log.info("POLICY_DELETED başarıyla gönderildi. Policy ID: {}", event.getPolicyId());
                    }
                });
    }

    public void sendPolicyActivated(PolicyActivatedEvent event) {
        log.info("POLICY_ACTIVATED gönderiliyor. Policy ID: {}", event.getPolicyId());
        kafkaTemplate.send(KafkaTopics.POLICY_ACTIVATED, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("POLICY_ACTIVATED gönderilemedi. Policy ID: {}, Hata: {}",
                                event.getPolicyId(), ex.getMessage());
                    } else {
                        log.info("POLICY_ACTIVATED başarıyla gönderildi. Policy ID: {}", event.getPolicyId());
                    }
                });
    }

    public void sendPaymentFailed(PaymentFailedEvent event) {
        log.info("PAYMENT_FAILED gönderiliyor. Policy ID: {}", event.getPolicyId());
        kafkaTemplate.send(KafkaTopics.PAYMENT_FAILED, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("PAYMENT_FAILED gönderilemedi. Policy ID: {}, Hata: {}",
                                event.getPolicyId(), ex.getMessage());
                    } else {
                        log.info("PAYMENT_FAILED başarıyla gönderildi. Policy ID: {}", event.getPolicyId());
                    }
                });
    }
}