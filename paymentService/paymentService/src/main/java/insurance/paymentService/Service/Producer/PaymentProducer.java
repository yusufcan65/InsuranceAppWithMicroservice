package insurance.paymentService.Service.Producer;

import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.PaymentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentCompleted(PaymentCompletedEvent event) {
        kafkaTemplate.send(KafkaTopics.PAYMENT_COMPLETED, event);
    }
}