package insurance.policyService.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Policy extends BaseEntity {

    private String policyNumber; // UUID string veya business ref

    private String branchCode;

    private UUID customerId;

    private UUID userId;

    private String status;

    private Double prim;

    private LocalDate tanzimDate;
    private LocalDate startDate;
    private LocalDate finishDate;

    private Integer remainderTime;
}