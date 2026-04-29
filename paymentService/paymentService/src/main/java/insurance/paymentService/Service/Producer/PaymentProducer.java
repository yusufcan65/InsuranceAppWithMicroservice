package insurance.paymentService.Service.Producer;

import insurance.insuranceCommon.Event.BaseEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, BaseEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, BaseEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentCompleted(PaymentCompletedEvent event) {
        kafkaTemplate.send(KafkaTopics.PAYMENT_COMPLETED, event);
    }
}