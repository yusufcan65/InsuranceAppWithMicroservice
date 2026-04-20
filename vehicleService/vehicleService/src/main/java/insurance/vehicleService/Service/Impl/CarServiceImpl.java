package insurance.vehicleService.Service.Impl;

import insurance.vehicleService.Dto.CarResponse;
import insurance.vehicleService.Entity.Cars;
import insurance.vehicleService.Repository.CarRepository;
import insurance.vehicleService.Service.CarService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarResponse getCarById(UUID id) {
        Cars car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("car not found by id"+id));

        return toResponse(car);
    }
    private CarResponse toResponse(Cars car){
        CarResponse carResponse = new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getModelYear(),
                car.getCarValue(),
                car.getEngineCapacity(),
                car.getHorsepower(),
                car.getFuelType(),
                car.getTransmission(),
                car.getColor()
        );
        return carResponse;
    }
}
