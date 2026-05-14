package insurance.paymentService.Consumer;

import insurance.insuranceCommon.KafkaTopics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterConsumer {

    Logger log = LoggerFactory.getLogger(DeadLetterConsumer.class);
    @KafkaListener(
            topics = {
                    KafkaTopics.POLICY_CREATED + ".DLT",
                    KafkaTopics.PAYMENT_COMPLETED + ".DLT",
                    KafkaTopics.POLICY_ACTIVATED + ".DLT",
                    KafkaTopics.PAYMENT_FAILED + ".DLT",
                    KafkaTopics.POLICY_UPDATED + ".DLT",
                    KafkaTopics.POLICY_DELETED + ".DLT"
            },
            groupId = "payment-dlt-group-v50",
            properties = {
                    "spring.json.value.default.type=java.lang.String",
                    "spring.json.trusted.packages=*"
            }
    )
    public void handleDeadLetter(
            ConsumerRecord<String, Object> record,
            @Header(value = KafkaHeaders.EXCEPTION_MESSAGE, required = false) Object exceptionMessage,
            @Header(value = KafkaHeaders.ORIGINAL_TOPIC, required = false) Object originalTopic) {


        String msg = "Hata yok";
        if (exceptionMessage instanceof byte[]) {
            msg = new String((byte[]) exceptionMessage);
        } else if (exceptionMessage != null) {
            msg = exceptionMessage.toString();
        }

        String topic = "Bilinmiyor";
        if (originalTopic instanceof byte[]) {
            topic = new String((byte[]) originalTopic);
        } else if (originalTopic != null) {
            topic = originalTopic.toString();
        }

        log.error("=== DEAD LETTER MESSAGE YAKALANDI ===");
        log.error("Original Topic: {}", topic);
        log.error("Exception: {}", msg);
        log.error("Message Payload: {}", record.value());
        log.error("===========================");
    }
}