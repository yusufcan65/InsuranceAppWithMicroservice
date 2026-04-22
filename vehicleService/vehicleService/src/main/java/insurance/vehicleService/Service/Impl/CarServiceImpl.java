package insurance.vehicleService.Service.Impl;

import insurance.vehicleService.Dto.CarRequest;
import insurance.vehicleService.Dto.CarResponse;
import insurance.vehicleService.Entity.Cars;
import insurance.vehicleService.Repository.CarRepository;
import insurance.vehicleService.Service.CarService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Cacheable(value = "cars", key = "#id")
    @Override
    public CarResponse getCarById(UUID id) {
        Cars car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("car not found by id"+id));

        return toResponse(car);
    }

    @Cacheable(value = "cars", key = "'allCars'")
    @Override
    public List<CarResponse> getAllCars() {
        List<Cars> cars = carRepository.findAll();

        return toResponseList(cars);
    }

    @CacheEvict(value = "cars", allEntries = true)
    @Override
    public CarResponse createCar(CarRequest carRequest) {

        Cars car = new Cars();
        car.setCarValue(carRequest.carValue());
        car.setBrand(carRequest.brand());
        car.setColor(carRequest.color());
        car.setFuelType(carRequest.fuelType());
        car.setHorsepower(carRequest.horsepower());
        car.setModel(carRequest.model());
        car.setModelYear(carRequest.modelYear());
        car.setTransmission(carRequest.transmission());
        car.setEngineCapacity(carRequest.engineCapacity());

        Cars toSave = carRepository.save(car);

        return toResponse(toSave);
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
    private List<CarResponse> toResponseList(List<Cars> cars){
        List<CarResponse> carResponses =cars.stream().map(this::toResponse).collect(Collectors.toList());
        return carResponses;
    }
}
