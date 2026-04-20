package trafficService.trafficService.Service;

import trafficService.trafficService.Dto.TrafficPolicyDetailResponse;
import trafficService.trafficService.Dto.TrafficRequest;
import trafficService.trafficService.Dto.TrafficResponse;

import java.util.List;

public interface TrafficService {

    TrafficPolicyDetailResponse createTrafficPolicyCreate(TrafficRequest trafficRequest);
    List<TrafficResponse> getAllTrafficPolicyCars();
}
