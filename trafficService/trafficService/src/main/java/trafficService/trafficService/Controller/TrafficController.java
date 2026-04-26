package trafficService.trafficService.Controller;


import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trafficService.trafficService.Dto.TrafficPolicyDetailResponse;
import trafficService.trafficService.Dto.TrafficRequest;
import trafficService.trafficService.Service.TrafficService;

@RestController
@RequestMapping("/v1/traffic")
public class TrafficController {

    private final TrafficService trafficService;

    public TrafficController(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<TrafficPolicyDetailResponse>> createTrafficPolicy(@RequestBody TrafficRequest trafficRequest){
        TrafficPolicyDetailResponse trafficPolicyDetailResponse = trafficService.createTrafficPolicyCreate(trafficRequest);
        return new ResponseEntity<>(RestResponse.of(trafficPolicyDetailResponse),HttpStatus.OK);
    }
}
