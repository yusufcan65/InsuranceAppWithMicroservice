package insurance.policyService.Service.Impl;

import insurance.insuranceCommon.Event.PolicyEvents.PolicyCreatedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyDeleteEvent;
import insurance.policyService.Dto.PolicyRequest;
import insurance.policyService.Dto.PolicyResponse;
import insurance.policyService.Dto.UpdatePolicyRequest;
import insurance.policyService.Entity.Policy;
import insurance.policyService.Exception.PolicyAlreadyActiveException;
import insurance.policyService.Exception.PolicyAlreadyPaidException;
import insurance.policyService.Exception.PolicyNotFoundException;
import insurance.policyService.Repository.PolicyRepository;
import insurance.policyService.Service.PolicyService;
import insurance.policyService.Service.Producer.PolicyProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;

    private final PolicyProducer policyProducer;

    public PolicyServiceImpl(PolicyRepository policyRepository, PolicyProducer policyProducer) {
        this.policyRepository = policyRepository;
        this.policyProducer = policyProducer;
    }

    @Override
    @Transactional
    public PolicyResponse CreatePolicy(PolicyRequest request) {
        Policy policy = new Policy();

        policy.setRemainderTime(request.remainderTime());
        policy.setPolicyNumber(generateUniquePolicyNumber());
        policy.setPrim(request.prim());
        policy.setCustomerNumber(request.customerNumber());
        policy.setBranchCode(request.branchCode());
        policy.setStatus(request.status());
        policy.setTanzimDate(request.tanzimDate());
        policy.setStartDate(request.startDate());
        policy.setFinishDate(request.finishDate());
        policy.setCustomerId(request.customerId());
        policy.setUserId(request.userId());

        Policy toSave = policyRepository.save(policy);

        policyProducer.sendPolicyCreated(new PolicyCreatedEvent(
                toSave.getId(),
                toSave.getPrim(),
                toSave.getPolicyNumber()
        ));


        return toResponse(toSave);
    }
    @Override
    @Transactional
    public PolicyResponse updatePolicy(UpdatePolicyRequest updateRequest){

        Policy policy = getById(updateRequest.policyId());

        if("P".equals(policy.getStatus())){
            throw new PolicyAlreadyActiveException("You can't update an active policy");
        }

        policy.setPrim(updateRequest.prim());
        Policy toUpdate = policyRepository.save(policy);

        policyProducer.sendPolicyUpdated(new PolicyCreatedEvent(
                toUpdate.getId(),
                toUpdate.getPrim(),
                toUpdate.getPolicyNumber()
        ));
        return toResponse(toUpdate);

    }
    @Override
    @Transactional
    public PolicyResponse deletePolicy(UUID policyId) {
        Policy policy = getById(policyId);

        if( "P".equals(policy.getStatus())){
            throw new PolicyAlreadyActiveException("You can't delete an active policy");
        }
        policyRepository.deleteById(policy.getId());
        policyProducer.sendPolicyDeleted(new PolicyDeleteEvent(
                policy.getId()
        ));

        return toResponse(policy);
    }

    @Override
    public List<PolicyResponse> getAll() {
        List<Policy> policies = policyRepository.findAll();
        return toResponseList(policies);
    }

    @Override
    public PolicyResponse getPolicyById(UUID policyId) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(()-> new PolicyNotFoundException("policy not found by policyId" +policyId));
        return toResponse(policy);
    }
    @Override
    public PolicyResponse getPolicyForPayment(UUID policyId){
        PolicyResponse policy = getPolicyById(policyId);

        if( "P".equals(policy.status()) || policy.paymentId() != null){
            throw new PolicyAlreadyPaidException("this policy value is pay please check policy payment status and try again");
        }
        return policy;
    }
    private Policy getById(UUID id){
        return policyRepository.findById(id)
                .orElseThrow(()-> new PolicyNotFoundException("policy not found by id "+id));
    }

    @Override
    public PolicyResponse activePolicy(UUID policyId, UUID paymentId) {

        Policy policy = getById(policyId);
        if( "P".equals(policy.getStatus()) || policy.getPaymentId() != null){
            throw new PolicyAlreadyPaidException("this policy value is pay please check policy paymant status and try again");
        }
        else {
            policy.setStatus("P");
            policy.setPaymentId(paymentId);
            policy.setStartDate(LocalDate.now());
            policy.setFinishDate(LocalDate.now().plusYears(1));
            policy.setRemainderTime(0);

            Policy toActive = policyRepository.save(policy);

            return toResponse(toActive);
        }
    }

    private Integer generateUniquePolicyNumber() {
        Random random = new Random();
        int policyNumber;
        boolean exists;
        do {
            policyNumber = 10000000 + random.nextInt(90000000);
            exists = policyRepository.existsByPolicyNumber(policyNumber);
        } while (exists);
        return policyNumber;
    }
    private PolicyResponse toResponse(Policy policy){
        PolicyResponse policyResponse = new PolicyResponse(
                policy.getId(),
                policy.getPolicyNumber(),
                policy.getPrim(),
                policy.getCustomerNumber(),
                policy.getBranchCode(),
                policy.getStatus(),
                policy.getRemainderTime(),
                policy.getTanzimDate(),
                policy.getStartDate(),
                policy.getFinishDate(),
                policy.getCustomerId(),
                policy.getUserId(),
                policy.getPaymentId()

        );
        return policyResponse;
    }
    private List<PolicyResponse> toResponseList(List<Policy> policies){
        List<PolicyResponse> policyResponses = policies.stream()
                .map(this::toResponse).collect(Collectors.toList());
        return policyResponses;
    }

    private Policy toEntity(PolicyRequest request){
        Policy policy = new Policy();
        policy.setBranchCode(request.branchCode());
        policy.setCustomerNumber(request.customerNumber());
        policy.setCustomerId(request.customerId());
        policy.setPrim(request.prim());
        policy.setFinishDate(request.finishDate());
        policy.setRemainderTime(request.remainderTime());
        return policy;
    }
}
