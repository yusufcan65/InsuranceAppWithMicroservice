package insurance.vehicleService.Controller;

import insurance.vehicleService.Dto.CarResponse;
import insurance.vehicleService.Service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

