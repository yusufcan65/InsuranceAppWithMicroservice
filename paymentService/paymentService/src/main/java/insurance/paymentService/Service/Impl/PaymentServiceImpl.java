package insurance.paymentService.Service.Impl;


import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.insuranceCommon.Event.PaymentEvents.PaymentFailedEvent;
import insurance.insuranceCommon.Event.PolicyEvents.PolicyActivatedEvent;
import insurance.paymentService.Dto.PaymentDetailResponse;
import insurance.paymentService.Dto.PaymentRequest;
import insurance.paymentService.Dto.PaymentResponse;
import insurance.paymentService.Dto.PolicyResponse;
import insurance.paymentService.Entity.Payment;
import insurance.paymentService.Entity.PaymentStatus;
import insurance.paymentService.Entity.PolicyProjection;
import insurance.paymentService.Exception.PaymentNotFoundException;
import insurance.paymentService.Repository.PaymentRepository;
import insurance.paymentService.Service.PaymentService;
import insurance.paymentService.Producer.PaymentProducer;
import insurance.paymentService.Service.PolicyProjectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;
    private final PolicyProjectionService policyProjectionService;

    Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentProducer paymentProducer,
                              PolicyProjectionService policyProjectionService) {
        this.paymentRepository = paymentRepository;
        this.paymentProducer = paymentProducer;
        this.policyProjectionService = policyProjectionService;
    }

    @Override
    public PaymentDetailResponse doPayment(PaymentRequest request) {

        // kafka eklendi ve bu nedenle feign client yapısı devre dışı bırakıldı
       /* RestResponse<PolicyResponse> restPolicyResponse = policyClient.getPolicyForPayment(request.policyId());
        PolicyResponse policyResponse = restPolicyResponse.getData();*/

        PolicyProjection policyProjection = policyProjectionService.getPolicyProjectionById(request.policyId());

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(policyProjection.getPrim());
        payment.setCvv(request.cvv());
        payment.setCardNumber(request.cardNumber());
        payment.setExpiryDate(request.expiryDate());
        payment.setCardOwner(request.cardOwner());
        payment.setPolicyId(policyProjection.getId());
        payment.setPolicyNumber(policyProjection.getPolicyNumber());
        payment.setStatus(PaymentStatus.PENDING);

        Payment toSave = paymentRepository.save(payment);

        paymentProducer.sendPaymentCompleted(
                new PaymentCompletedEvent(
                        toSave.getPolicyId(),
                        toSave.getId(),
                        toSave.getAmount(),
                        toSave.getPolicyNumber()
                )
        );


        // kafka eklendi ve bu şekilde feign client devre dışı bırakıldı
      //  PolicyResponse updatePolicy = policyClient.activePolicy(policyResponse.id(), toSave.getId());

        PolicyResponse policyResponse = new PolicyResponse(
                toSave.getPolicyId(),
                toSave.getPolicyNumber(),
                toSave.getAmount(),
                toSave.getId()
        );

        PaymentResponse paymentResponse = toResponse(payment);

        PaymentDetailResponse paymentDetailResponse = new PaymentDetailResponse(paymentResponse,policyResponse);

        return paymentDetailResponse;
    }

    public void confirmPayment(PolicyActivatedEvent event) {
        Payment payment = getById(event.getPaymentId());

        payment.setStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
        log.info("SAGA: Ödeme CONFIRMED yapıldı: {}", event.getPaymentId());
    }

    public void failPayment(PaymentFailedEvent event) {
        Payment payment = getById(event.getPaymentId());

        payment.setStatus(PaymentStatus.FAILED);
        paymentRepository.save(payment);
        log.error("SAGA: Ödeme FAILED yapıldı: {}, Sebep: {}", event.getPaymentId(), event.getReason());
    }

    private Payment getById(UUID id){
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(" Payment Not Found By ID : " + id));
    }

    private PaymentResponse toResponse(Payment payment){
        PaymentResponse paymentResponse = new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPolicyNumber(),
                payment.getPaymentDate(),
                payment.getCardNumber(),
                payment.getCardOwner(),
                payment.getPolicyId()
        );
        return paymentResponse;
    }
}
