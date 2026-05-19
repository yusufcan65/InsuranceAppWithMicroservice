package insurance.homeService.Controller;

import insurance.homeService.Dto.DaskRequest;
import insurance.homeService.Dto.DaskResponse;
import insurance.homeService.Service.HomeService;
import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home-policies")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<DaskResponse>> createDaskPolicy(@RequestBody DaskRequest request){
        DaskResponse daskResponse = homeService.createDaskPolicy(request);
        return new ResponseEntity<>(RestResponse.of(daskResponse), HttpStatus.CREATED);
    }
}
