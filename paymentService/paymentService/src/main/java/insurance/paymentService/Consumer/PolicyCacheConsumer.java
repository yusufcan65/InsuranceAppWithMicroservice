package insurance.paymentService.Consumer;


import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.paymentService.Entity.PaymentStatus;
import insurance.paymentService.Entity.PolicyCache;
import insurance.paymentService.Repository.PaymentRepository;
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
    private final PaymentRepository paymentRepository;

    Logger log = LoggerFactory.getLogger(PolicyCacheConsumer.class);

    public PolicyCacheConsumer(PolicyCacheRepository policyCacheRepository, PaymentRepository paymentRepository) {
        this.policyCacheRepository = policyCacheRepository;
        this.paymentRepository = paymentRepository;
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
    public void consumePolicyDeleted(PolicyDeleteEvent event) {
        log.info("Poliçe SİLME mesajı alındı. Event ID: {}, Poliçe ID: {}",
                event.getEventId(), event.getPolicyId());

        UUID policyId = event.getPolicyId();

        if (policyCacheRepository.existsById(policyId)) {
            policyCacheRepository.deleteById(policyId);
            log.info("Poliçe ID: {} başarıyla cache'den temizlendi.", policyId);
        } else {
            log.warn("Silinmek istenen poliçe cache'de bulunamadı: {}", policyId);
        }
    }


    // Poliçe aktifleşti → ödemeyi CONFIRMED yap
    @KafkaListener(topics = KafkaTopics.POLICY_ACTIVATED, groupId = "payment-cache-group-v99")
    public void handlePolicyActivated(PolicyActivatedEvent event) {
        log.info("Policy activated alındı, ödeme confirmed yapılıyor: {}", event.getPaymentId());

        paymentRepository.findById(event.getPaymentId()).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.CONFIRMED);
            paymentRepository.save(payment);
            log.info("Ödeme CONFIRMED yapıldı: {}", event.getPaymentId());
        });
    }

    // Poliçe aktifleşemedi → ödemeyi FAILED yap
    @KafkaListener(topics = KafkaTopics.PAYMENT_FAILED, groupId = "payment-cache-group-v99")
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.error("Payment failed alındı, ödeme iptal ediliyor: {}", event.getPaymentId());

        paymentRepository.findById(event.getPaymentId()).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            log.error("Ödeme FAILED yapıldı: {}", event.getPaymentId());
        });
    }
}