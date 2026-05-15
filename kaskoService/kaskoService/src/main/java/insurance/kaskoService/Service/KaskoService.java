package insurance.kaskoService.Service;



import insurance.kaskoService.Dto.KaskoPolicyDetailResponse;
import insurance.kaskoService.Dto.KaskoRequest;
import insurance.kaskoService.Dto.KaskoResponse;

import java.util.List;

public interface KaskoService {

    KaskoPolicyDetailResponse createKaskoPolicyCreate(KaskoRequest trafficRequest);
    List<KaskoResponse> getAllKaskoPolicyCars();
}
