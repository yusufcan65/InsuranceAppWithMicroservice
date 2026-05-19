package insurance.vehicleService.Controller;

import insurance.insuranceCommon.RestResponse;
import insurance.vehicleService.Dto.CarRequest;
import insurance.vehicleService.Dto.CarResponse;
import insurance.vehicleService.Service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicles")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/internal/{id}")
    public ResponseEntity<RestResponse<CarResponse>> getCarForFeign(@PathVariable UUID id){
        CarResponse carResponse = carService.getCarById(id);
        return new ResponseEntity<>(RestResponse.of(carResponse), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<CarResponse>>> getAllCars(){
        List<CarResponse> carResponses = carService.getAllCars();
        return new ResponseEntity<>(RestResponse.of(carResponses),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RestResponse<CarResponse>> createCar(@RequestBody CarRequest request){
        CarResponse carResponse = carService.createCar(request);
        return new ResponseEntity<>(RestResponse.of(carResponse),HttpStatus.CREATED);
    }

}

