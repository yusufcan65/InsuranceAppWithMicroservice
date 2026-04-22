package insurance.vehicleService.Dto;

public record CarRequest(
        String brand,
        String model,
        Integer modelYear,
        Double carValue,
        Integer engineCapacity,
        Integer horsepower,
        String fuelType,
        String transmission,
        String color
) {}