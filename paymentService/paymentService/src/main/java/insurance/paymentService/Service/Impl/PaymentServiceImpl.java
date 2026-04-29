package insurance.paymentService.Service.Impl;

import insurance.insuranceCommon.Event.PaymentEvents.PaymentCompletedEvent;
import insurance.paymentService.Dto.*;
import insurance.paymentService.Entity.Payment;
import insurance.paymentService.Entity.PolicyCache;
import insurance.paymentService.Exception.ResourceNotFoundException;
import insurance.paymentService.Repository.PaymentRepository;
import insurance.paymentService.Repository.PolicyCacheRepository;
import insurance.paymentService.Service.PaymentService;
import insurance.paymentService.Service.Producer.PaymentProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;
    private final PolicyCacheRepository policyCacheRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentProducer paymentProducer, PolicyCacheRepository policyCacheRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentProducer = paymentProducer;
        this.policyCacheRepository = policyCacheRepository;
    }

    @Override
    public PaymentDetailResponse doPayment(PaymentRequest request) {

        // kafka eklendi ve bu nedenle feign client yapısı devre dışı bırakıldı
       /* RestResponse<PolicyResponse> restPolicyResponse = policyClient.getPolicyForPayment(request.policyId());
        PolicyResponse policyResponse = restPolicyResponse.getData();*/

        PolicyCache policyCache = policyCacheRepository.findById(request.policyId())
                .orElseThrow(()-> new ResourceNotFoundException("HATA: Poliçe bilgisi yerel veritabanında bulunamadı! (Henüz Kafka'dan gelmemiş olabilir)"));

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(policyCache.getPrim());
        payment.setCvv(request.cvv());
        payment.setCardNumber(request.cardNumber());
        payment.setExpiryDate(request.expiryDate());
        payment.setCardOwner(request.cardOwner());
        payment.setPolicyId(policyCache.getId());
        payment.setPolicyNumber(policyCache.getPolicyNumber());

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

        PaymentResponse paymentResponse = toResponse(payment);

        PaymentDetailResponse paymentDetailResponse = new PaymentDetailResponse(paymentResponse,null);

        return paymentDetailResponse;
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
