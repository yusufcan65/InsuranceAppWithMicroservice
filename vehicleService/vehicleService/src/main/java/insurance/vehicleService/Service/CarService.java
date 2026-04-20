package insurance.vehicleService.Service;

import insurance.vehicleService.Dto.CarResponse;

import java.util.UUID;

public interface CarService {

    CarResponse getCarById(UUID id);
}
