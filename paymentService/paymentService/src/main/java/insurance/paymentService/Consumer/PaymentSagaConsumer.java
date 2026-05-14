package insurance.paymentService.Consumer;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.insuranceCommon.KafkaTopics;
import insurance.paymentService.Service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentSagaConsumer {

    private final PaymentService paymentService;
    Logger log = LoggerFactory.getLogger(PaymentSagaConsumer.class);

    public PaymentSagaConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @KafkaListener(topics = KafkaTopics.POLICY_ACTIVATED, groupId = "payment-saga-group-v2")
    public void handlePolicyActivated( @Payload PolicyActivatedEvent event) {
        log.info("SAGA: POLICY_ACTIVATED eventi alındı. Payment ID: {}", event.getPaymentId());
        paymentService.confirmPayment(event);
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_FAILED, groupId = "payment-saga-group-v2")
    public void handlePaymentFailed(@Payload PaymentFailedEvent event) {
        log.error("SAGA: PAYMENT_FAILED eventi alındı. Payment ID: {}", event.getPaymentId());
        paymentService.failPayment(event);
    }
}
