package insurance.paymentService.Consumer;


import insurance.insuranceCommon.PolicyCreatedEvent;
import insurance.paymentService.Entity.PolicyCache;
import insurance.paymentService.Repository.PolicyCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import insurance.insuranceCommon.KafkaTopics;

import java.util.UUID;

@Component
public class PolicyCacheConsumer {
    private final PolicyCacheRepository policyCacheRepository;

    Logger log = LoggerFactory.getLogger(PolicyCacheConsumer.class);

    public PolicyCacheConsumer(PolicyCacheRepository policyCacheRepository) {
        this.policyCacheRepository = policyCacheRepository;
    }

    @KafkaListener(topics = KafkaTopics.POLICY_CREATED, groupId = "payment-cache-group-v99")
    public void consumePolicyCreated(PolicyCreatedEvent event) {
        log.info("Yeni poliçe haberi alındı: {}", event.getId());

        try {
            // 1. Veriyi oluştururken null kontrolü yapalım
            if (event.getId() == null) {
                log.error("HATA: Gelen Poliçe ID'si NULL!");
                return;
            }

            PolicyCache cache = new PolicyCache();
            cache.setId(event.getId());
            cache.setPrim(event.getPrim());
            cache.setPolicyNumber(event.getPolicyNumber());

            // 2. Kaydetmeyi dene
            policyCacheRepository.save(cache);
            log.info("BAŞARIYLA CACHE'E YAZILDI: {}", event.getId());

        } catch (Exception e) {
            // BURASI ÇOK ÖNEMLİ: Hatanın detayını burada göreceğiz
            log.error("VERİTABANINA KAYDEDERKEN HATA OLUŞTU!");
            log.error("Hata Mesajı: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = KafkaTopics.POLICY_UPDATED, groupId = "payment-cache-group-v99")
    public void consumePolicyUpdated(PolicyCreatedEvent event) {
        log.info("Poliçe GÜNCELLEME mesajı alındı: {}", event.getId());
        policyCacheRepository.findById(event.getId()).ifPresent(cache -> {
            cache.setPrim(event.getPrim());
            cache.setPolicyNumber(event.getPolicyNumber());
            policyCacheRepository.save(cache);
        });
    }

    @KafkaListener(
            topics = KafkaTopics.POLICY_DELETED,
            groupId = "payment-cache-group-v99",
            properties = {"spring.json.value.default.type=java.lang.String"}
    )
    public void consumePolicyDeleted(String message) {
        log.info("Poliçe SİLME mesajı alındı: {}", message);

        UUID policyId = UUID.fromString(message.replace("\"", ""));

        if (policyCacheRepository.existsById(policyId)) {
            policyCacheRepository.deleteById(policyId);
            log.info("Poliçe cache'den temizlendi.");
        }
    }
}