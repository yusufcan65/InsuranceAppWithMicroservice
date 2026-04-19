package insurance.homeService.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record PolicyResponse(
        UUID id,
        Integer policyNumber,
        Double prim,
        Integer customerNumber,
        String branchCode,
        String status,
        Integer remainderTime,
        LocalDate tanzimDate,
        LocalDate startDate,
        LocalDate finishDate,
        UUID customerId,
        UUID userId,
        UUID paymentId
) {
}
