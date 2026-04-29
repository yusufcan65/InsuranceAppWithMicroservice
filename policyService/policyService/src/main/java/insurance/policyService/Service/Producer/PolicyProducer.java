package insurance.policyService.Service.Producer;

import insurance.insuranceCommon.Event.BaseEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PolicyProducer {
    private final KafkaTemplate<String, BaseEvent> kafkaTemplate;
    Logger log = LoggerFactory.getLogger(PolicyProducer.class);

    public PolicyProducer(KafkaTemplate<String, BaseEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPolicyCreated(PolicyCreatedEvent event) {
        log.info("Poliçe oluşturma işlemi gönderiliyor: {}", event.getId());
        kafkaTemplate.send(KafkaTopics.POLICY_CREATED, event);
    }

    public void sendPolicyDeleted(PolicyDeleteEvent event) {
        kafkaTemplate.send(KafkaTopics.POLICY_DELETED, event);
    }
    public void sendPolicyUpdated(PolicyCreatedEvent event) {
        kafkaTemplate.send(KafkaTopics.POLICY_UPDATED, event);
    }
}