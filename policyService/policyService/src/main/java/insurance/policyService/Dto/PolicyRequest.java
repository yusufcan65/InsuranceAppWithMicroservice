package insurance.policyService.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record PolicyRequest(
        Double prim,
        Integer customerNumber,
        String branchCode,
        String status,
        Integer remainderTime,
        LocalDate tanzimDate,
        LocalDate startDate,
        LocalDate finishDate,
        UUID customerId,
        UUID userId
) {
}
