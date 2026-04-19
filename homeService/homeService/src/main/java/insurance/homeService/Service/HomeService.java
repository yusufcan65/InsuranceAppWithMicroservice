package insurance.homeService.Service;

import insurance.homeService.Dto.DaskRequest;
import insurance.homeService.Dto.DaskResponse;
import insurance.homeService.Dto.HomeResponse;

import java.util.List;

public interface HomeService {

    DaskResponse createDaskPolicy(DaskRequest daskRequest);
    List<HomeResponse> getAllHomes();


}
