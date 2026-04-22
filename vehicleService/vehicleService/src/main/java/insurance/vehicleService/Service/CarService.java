package insurance.vehicleService.Service;

import insurance.vehicleService.Dto.CarRequest;
import insurance.vehicleService.Dto.CarResponse;

import java.util.List;
import java.util.UUID;

public interface CarService {

    CarResponse getCarById(UUID id);
    List<CarResponse> getAllCars();

    CarResponse createCar(CarRequest carRequest);
}
