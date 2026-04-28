package insurance.policyService.Config;

import insurance.insuranceCommon.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic policyCreatedTopic() {
        return TopicBuilder.name(KafkaTopics.POLICY_CREATED)
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic policyUpdatedTopic() {
        return TopicBuilder.name(KafkaTopics.POLICY_UPDATED)
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic policyDeletedTopic() {
        return TopicBuilder.name(KafkaTopics.POLICY_DELETED)
                .partitions(1)
                .replicas(1)
                .build();
    }
}