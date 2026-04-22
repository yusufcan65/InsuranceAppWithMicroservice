package insurance.vehicleService.Controller;

import insurance.vehicleService.Dto.CarRequest;
import insurance.vehicleService.Dto.CarResponse;
import insurance.vehicleService.Service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/vehicle")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/internal/feign/{id}")
    public ResponseEntity<CarResponse> getCarForFeign(@PathVariable UUID id){
        CarResponse carResponse = carService.getCarById(id);
        return new ResponseEntity<>(carResponse, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<CarResponse>> getAllCars(){
        List<CarResponse> carResponses = carService.getAllCars();
        return new ResponseEntity<>(carResponses,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest request){
        CarResponse carResponse = carService.createCar(request);
        return new ResponseEntity<>(carResponse,HttpStatus.OK);
    }

}

