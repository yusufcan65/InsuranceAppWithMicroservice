package insurance.paymentService.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Payment extends BaseEntity {

    private UUID policyId;

    private Double amount;

    private LocalDate paymentDate;

    private String cardNumber;
    private String cardOwner;
    private String expiryDate;
    private String cvv;

    private String status;
}