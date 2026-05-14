package insurance.paymentService.Producer;


import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    Logger log = LoggerFactory.getLogger(PaymentProducer.class);

    public PaymentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentCompleted(PaymentCompletedEvent event) {
        log.info("PAYMENT_COMPLETED gönderiliyor. Policy ID: {}, Payment ID: {}",
                event.getPolicyId(), event.getPaymentId());

        sendMessage(KafkaTopics.PAYMENT_COMPLETED, event.getPolicyId().toString(), event);
    }

    public void sendPaymentFailed(PaymentFailedEvent event) {
        log.error("PAYMENT_FAILED gönderiliyor. Policy ID: {}, Sebep: {}",
                event.getPolicyId(), event.getReason());

        sendMessage(KafkaTopics.PAYMENT_FAILED, event.getPolicyId().toString(), event);
    }

    private void sendMessage(String topic, String key, Object event) {
        kafkaTemplate.send(topic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Mesaj gönderilemedi! Topic: {}, Hata: {}", topic, ex.getMessage());
                    } else {
                        log.info("Mesaj başarıyla iletildi. Topic: {}, Offset: {}",
                                topic, result.getRecordMetadata().offset());
                    }
                });
    }
}