package insurance.paymentService.Service.Impl;

import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.paymentService.Entity.PolicyProjection;
import insurance.paymentService.Exception.InvalidPolicyProjectionEventException;
import insurance.paymentService.Exception.PolicyProjectionAlreadyExistsException;
import insurance.paymentService.Exception.PolicyProjectionNotFoundException;
import insurance.paymentService.Repository.PolicyProjectionRepository;
import insurance.paymentService.Service.PolicyProjectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PolicyProjectionServiceImpl implements PolicyProjectionService {
    private final PolicyProjectionRepository policyProjectionRepository;
    Logger log = LoggerFactory.getLogger(PolicyProjectionServiceImpl.class);

    public PolicyProjectionServiceImpl(PolicyProjectionRepository policyProjectionRepository) {
        this.policyProjectionRepository = policyProjectionRepository;
    }


    public void createPolicyProjection(PolicyCreatedEvent event) {
        if (event.getId() == null) {
            log.error("HATA: PolicyProjection oluşturulurken Poliçe ID NULL geldi! Event: {}", event);
            throw new InvalidPolicyProjectionEventException("Policy ID cannot be null for policy projection creation");
        }

        if (policyProjectionRepository.existsById(event.getId())) {
            log.warn("PolicyProjection zaten mevcut, oluşturma işlemi atlanıyor. ID: {}", event.getId());
            throw new PolicyProjectionAlreadyExistsException("Bu ID ile zaten bir projeksiyon var: " + event.getId());
        }

        PolicyProjection projection = new PolicyProjection();

        projection.setId(event.getId());
        projection.setPrim(event.getPrim());
        projection.setPolicyNumber(event.getPolicyNumber());

        policyProjectionRepository.save(projection);

        log.info("Poliçe PolicyProjection başarıyla oluşturuldu: ID={}, No={}", event.getId(), event.getPolicyNumber());
    }

    public void updatePolicyProjection(PolicyCreatedEvent event) {

        PolicyProjection policyProjection = getPolicyProjectionById(event.getId());

        policyProjection.setPrim(event.getPrim());
        policyProjection.setPolicyNumber(event.getPolicyNumber());

        policyProjectionRepository.save(policyProjection);
        log.info("Poliçe PolicyProjection güncellendi: {}", event.getId());

    }

    public void deletePolicyProjection(PolicyDeleteEvent event) {

        PolicyProjection policyProjection = getPolicyProjectionById(event.getPolicyId());

        policyProjectionRepository.delete(policyProjection);

        log.info("Poliçe PolicyProjection başarıyla silindi. ID: {}", event.getPolicyId());
    }

    @Override
    public PolicyProjection getPolicyProjectionById(UUID id){
        PolicyProjection policyProjection = policyProjectionRepository.findById(id)
                .orElseThrow(()->new PolicyProjectionNotFoundException("Policy PolicyProjection Not found by id : "+ id));
        return policyProjection;
    }
    @Override
    public List<PolicyProjection> getAllPoliciesProjections() {
        return policyProjectionRepository.findAll();
    }
}
