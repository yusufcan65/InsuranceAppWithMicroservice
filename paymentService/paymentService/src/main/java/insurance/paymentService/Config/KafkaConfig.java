package insurance.paymentService.Config;

import insurance.insuranceCommon.KafkaTopics;
import insurance.paymentService.Exception.PaymentNotFoundException;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.messaging.converter.MessageConversionException;

@Configuration
public class KafkaConfig {

    private final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    @Bean
    public NewTopic paymentCompletedTopic() {
        return TopicBuilder.name(KafkaTopics.PAYMENT_COMPLETED).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic paymentFailedTopic() {
        return TopicBuilder.name(KafkaTopics.PAYMENT_FAILED).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic policyCreatedDltTopic() {
        return TopicBuilder.name(KafkaTopics.POLICY_CREATED + ".DLT").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic policyUpdatedDltTopic() {
        return TopicBuilder.name(KafkaTopics.POLICY_UPDATED + ".DLT").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic policyDeletedDltTopic() {
        return TopicBuilder.name(KafkaTopics.POLICY_DELETED + ".DLT").partitions(1).replicas(1).build();
    }

    @Bean
    public DefaultErrorHandler defaultErrorHandler(KafkaTemplate<String, Object> kafkaTemplate) {

        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (record, exception) -> {
                    if (record.topic().endsWith(".DLT")) {
                        log.error("--- DLT DÖNGÜSÜ ENGELLENDİ --- Topic: {}, Hata: {}",
                                record.topic(), exception.getMessage());
                        return null;
                    }

                    log.error("### Mesaj DLT'ye gönderiliyor ### Kaynak Topic: {}, Hata: {}",
                            record.topic(), exception.getMessage());
                    return new TopicPartition(record.topic() + ".DLT", record.partition());
                });

        ExponentialBackOffWithMaxRetries backOff = new ExponentialBackOffWithMaxRetries(3);
        backOff.setInitialInterval(1000L);
        backOff.setMultiplier(2.0);
        backOff.setMaxInterval(10000L);

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);


        errorHandler.addNotRetryableExceptions(
                DeserializationException.class,
                MessageConversionException.class,
                PaymentNotFoundException.class,
                IllegalArgumentException.class
        );

        return errorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory,
            DefaultErrorHandler defaultErrorHandler) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(defaultErrorHandler);
        return factory;
    }
}