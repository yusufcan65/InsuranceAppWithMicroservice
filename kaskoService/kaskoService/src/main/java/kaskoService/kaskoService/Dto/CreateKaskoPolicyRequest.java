package kaskoService.kaskoService.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateKaskoPolicyRequest(

        Double prim,
        Integer customerNumber,
        String branchCode,
        String status,
        Integer remainderTime,
        LocalDate tanzimDate,
        LocalDate finishDate,
        UUID customerId,
        UUID userId
) {
}
