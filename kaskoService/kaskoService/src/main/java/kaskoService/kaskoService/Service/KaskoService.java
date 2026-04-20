package kaskoService.kaskoService.Service;



import kaskoService.kaskoService.Dto.KaskoPolicyDetailResponse;
import kaskoService.kaskoService.Dto.KaskoRequest;
import kaskoService.kaskoService.Dto.KaskoResponse;

import java.util.List;

public interface KaskoService {

    KaskoPolicyDetailResponse createKaskoPolicyCreate(KaskoRequest trafficRequest);
    List<KaskoResponse> getAllKaskoPolicyCars();
}
