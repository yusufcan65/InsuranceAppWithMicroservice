package insurance.kaskoService.Controller;

import insurance.kaskoService.Dto.KaskoPolicyDetailResponse;
import insurance.kaskoService.Dto.KaskoRequest;
import insurance.kaskoService.Service.KaskoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/kasko")
public class KaskoController {

    private final KaskoService kaskoService;

    public KaskoController(KaskoService kaskoService) {
        this.kaskoService = kaskoService;
    }

    @PostMapping("/create")
    public ResponseEntity<KaskoPolicyDetailResponse> createKaskoPolicy(@RequestBody KaskoRequest kaskoRequest){
        KaskoPolicyDetailResponse kaskoPolicyDetailResponse = kaskoService.createKaskoPolicyCreate(kaskoRequest);
        return new ResponseEntity<>(kaskoPolicyDetailResponse, HttpStatus.OK);
    }
}
