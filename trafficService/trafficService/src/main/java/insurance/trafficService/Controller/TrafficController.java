package insurance.trafficService.Controller;


import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import insurance.trafficService.Dto.TrafficPolicyDetailResponse;
import insurance.trafficService.Dto.TrafficRequest;
import insurance.trafficService.Service.TrafficService;

@RestController
@RequestMapping("/api/v1/traffic-policies")
public class TrafficController {

    private final TrafficService trafficService;

    public TrafficController(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<TrafficPolicyDetailResponse>> createTrafficPolicy(@RequestBody TrafficRequest trafficRequest){
        TrafficPolicyDetailResponse trafficPolicyDetailResponse = trafficService.createTrafficPolicyCreate(trafficRequest);
        return new ResponseEntity<>(RestResponse.of(trafficPolicyDetailResponse),HttpStatus.CREATED);
    }
}
