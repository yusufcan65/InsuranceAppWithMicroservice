package insurance.trafficService.Service;

import insurance.trafficService.Dto.TrafficPolicyDetailResponse;
import insurance.trafficService.Dto.TrafficRequest;
import insurance.trafficService.Dto.TrafficResponse;

import java.util.List;

public interface TrafficService {

    TrafficPolicyDetailResponse createTrafficPolicyCreate(TrafficRequest trafficRequest);
    List<TrafficResponse> getAllTrafficPolicyCars();
}
