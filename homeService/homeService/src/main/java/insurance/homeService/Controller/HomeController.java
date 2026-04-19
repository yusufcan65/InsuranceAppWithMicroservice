package insurance.homeService.Controller;

import insurance.homeService.Dto.DaskRequest;
import insurance.homeService.Dto.DaskResponse;
import insurance.homeService.Service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping("/create")
    public ResponseEntity<DaskResponse> createDaskPolicy(@RequestBody DaskRequest request){
        DaskResponse daskResponse = homeService.createDaskPolicy(request);
        return new ResponseEntity<>(daskResponse, HttpStatus.OK);
    }
}
