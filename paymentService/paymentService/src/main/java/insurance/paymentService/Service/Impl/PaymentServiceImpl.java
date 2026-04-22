package insurance.paymentService.Service.Impl;

import insurance.paymentService.Client.PolicyClient;
import insurance.paymentService.Dto.PaymentDetailResponse;
import insurance.paymentService.Dto.PaymentRequest;
import insurance.paymentService.Dto.PaymentResponse;
import insurance.paymentService.Dto.PolicyResponse;
import insurance.paymentService.Entity.Payment;
import insurance.paymentService.Repository.PaymentRepository;
import insurance.paymentService.Service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PolicyClient policyClient;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PolicyClient policyClient) {
        this.paymentRepository = paymentRepository;
        this.policyClient = policyClient;
    }

    @Override
    public PaymentDetailResponse doPayment(PaymentRequest request) {

        PolicyResponse policyResponse = policyClient.getPolicyForFeign(request.policyId());

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(policyResponse.prim());
        payment.setCvv(request.cvv());
        payment.setCardNumber(request.cardNumber());
        payment.setExpiryDate(request.expiryDate());
        payment.setCardOwner(request.cardOwner());
        payment.setPolicyId(policyResponse.id());
        payment.setPolicyNumber(policyResponse.policyNumber());

        Payment toSave = paymentRepository.save(payment);

        PolicyResponse updatePolicy = policyClient.activePolicy(policyResponse.id(), toSave.getId());

        PaymentResponse paymentResponse = toResponse(payment);

        PaymentDetailResponse paymentDetailResponse = new PaymentDetailResponse(paymentResponse,updatePolicy);

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
