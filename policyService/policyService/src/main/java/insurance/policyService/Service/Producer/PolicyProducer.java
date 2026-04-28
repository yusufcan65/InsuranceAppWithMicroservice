package insurance.policyService.Service.Producer;

import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.PolicyCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class PolicyProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    Logger log = LoggerFactory.getLogger(PolicyProducer.class);

    public PolicyProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPolicyCreated(PolicyCreatedEvent event) {
        log.info("Poliçe oluşturma işlemi gönderiliyor: {}", event.getId());
        kafkaTemplate.send(KafkaTopics.POLICY_CREATED, event);
    }

    public void sendPolicyDeleted(UUID policyId) {
        kafkaTemplate.send(KafkaTopics.POLICY_DELETED, policyId);
    }
    public void sendPolicyUpdated(PolicyCreatedEvent event) {
        kafkaTemplate.send(KafkaTopics.POLICY_UPDATED, event);
    }
}