package insurance.policyService.Service.Impl;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.policyService.Exception.PolicyNotFoundException;
import insurance.policyService.Producer.PolicyProducer;
import insurance.policyService.Service.PolicySagaService;
import insurance.policyService.Service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PolicySagaServiceImpl implements PolicySagaService {

    private final PolicyService policyService;
    private final PolicyProducer policyProducer;

    private final Logger log = LoggerFactory.getLogger(PolicySagaService.class);

    public PolicySagaServiceImpl(PolicyService policyService, PolicyProducer policyProducer) {
        this.policyService = policyService;
        this.policyProducer = policyProducer;
    }

    @Override
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        try {
            policyService.activePolicy(event.getPolicyId(), event.getPaymentId());
            log.info("SAGA: Poliçe aktifleştirildi. Policy ID: {}", event.getPolicyId());

            policyProducer.sendPolicyActivated(
                    new PolicyActivatedEvent(
                            event.getPolicyId(),
                            event.getPaymentId(),
                            event.getPolicyNumber()
                    )
            );

        } catch (PolicyNotFoundException e) {
            log.error("SAGA: Poliçe bulunamadı. Policy ID: {}", event.getPolicyId());

            policyProducer.sendPaymentFailed(
                    new PaymentFailedEvent(
                            event.getPolicyId(),
                            event.getPaymentId(),
                            e.getMessage()
                    )
            );
            throw e;

        } catch (Exception e) {
            log.error("SAGA: Poliçe aktifleştirilemedi. Policy ID: {}, Hata: {}",
                    event.getPolicyId(), e.getMessage());

            policyProducer.sendPaymentFailed(
                    new PaymentFailedEvent(
                            event.getPolicyId(),
                            event.getPaymentId(),
                            e.getMessage()
                    )
            );
            throw e;
        }
    }

    @Override
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.error("SAGA: PAYMENT_FAILED alındı. Policy ID: {}, Sebep: {}",
                event.getPolicyId(), event.getReason());
        try {
            policyService.rejectPolicy(event.getPolicyId());
            log.info("SAGA: Poliçe başarıyla geri alındı. Policy ID: {}", event.getPolicyId());
        } catch (PolicyNotFoundException e) {
            log.error("SAGA: Geri alınacak poliçe bulunamadı. Policy ID: {}", event.getPolicyId());
            throw e;
        } catch (Exception e) {
            log.error("SAGA: Poliçe geri alınamadı. Policy ID: {}, Hata: {}",
                    event.getPolicyId(), e.getMessage());
            throw e;
        }
    }
}

