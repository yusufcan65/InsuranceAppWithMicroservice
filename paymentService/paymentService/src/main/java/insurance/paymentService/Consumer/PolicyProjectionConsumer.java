package insurance.paymentService.Consumer;


import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.paymentService.Service.PolicyProjectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PolicyProjectionConsumer {

    private final PolicyProjectionService policyProjectionService;
    Logger log = LoggerFactory.getLogger(PolicyProjectionConsumer.class);

    public PolicyProjectionConsumer(PolicyProjectionService policyProjectionService) {
        this.policyProjectionService = policyProjectionService;
    }

    @KafkaListener(topics = KafkaTopics.POLICY_CREATED, groupId = "payment-final-group-v110")
    public void consumePolicyCreated( @Payload PolicyCreatedEvent event) {
        log.info("POLICY_CREATED eventi alındı: {}", event.getId());
        policyProjectionService.createPolicyProjection(event);
    }

    @KafkaListener(topics = KafkaTopics.POLICY_UPDATED, groupId = "payment-final-group-v110")
    public void consumePolicyUpdated( @Payload PolicyCreatedEvent event) {
        log.info("POLICY_UPDATED eventi alındı: {}", event.getId());
        policyProjectionService.updatePolicyProjection(event);
    }

    @KafkaListener(
            topics = KafkaTopics.POLICY_DELETED,
            groupId = "payment-final-group-v110",
            properties = {"spring.json.value.default.type=java.lang.String"}
    )
    public void consumePolicyDeleted( @Payload PolicyDeleteEvent event) {
        log.info("POLICY_DELETED eventi alındı: {}", event.getPolicyId());
        policyProjectionService.deletePolicyProjection(event);
    }
}