package insurance.paymentService.Config;

import insurance.insuranceCommon.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic paymentCompletedTopic() {
        return TopicBuilder.name(KafkaTopics.PAYMENT_COMPLETED)
                .partitions(1)
                .replicas(1)
                .build();
    }

}