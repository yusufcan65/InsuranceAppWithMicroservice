package insurance.vehicleService.Dto;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String brand,
        String model,
        Integer modelYear,
        Double carValue,
        Integer engineCapacity,
        Integer horsepower,
        String fuelType,
        String transmission,
        String color

) {
}
