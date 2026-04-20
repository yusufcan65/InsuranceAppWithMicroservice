package trafficService.trafficService.Service.Impl;

import org.springframework.stereotype.Service;
import trafficService.trafficService.Client.CustomerClient;
import trafficService.trafficService.Client.PolicyClient;
import trafficService.trafficService.Client.UserClient;
import trafficService.trafficService.Client.VehicleClient;
import trafficService.trafficService.Dto.*;
import trafficService.trafficService.Entity.TrafficPolicyCars;
import trafficService.trafficService.Repository.TrafficRepository;
import trafficService.trafficService.Service.TrafficService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrafficServiceImpl implements TrafficService {

    private final PolicyClient policyClient;
    private final CustomerClient customerClient;
    private final UserClient userClient;
    private final TrafficRepository trafficRepository;
    private final VehicleClient vehicleClient;

    public TrafficServiceImpl(PolicyClient policyClient, CustomerClient customerClient,
                              UserClient userClient, TrafficRepository trafficRepository,
                              VehicleClient vehicleClient) {
        this.policyClient = policyClient;
        this.customerClient = customerClient;
        this.userClient = userClient;
        this.trafficRepository = trafficRepository;
        this.vehicleClient = vehicleClient;
    }

    @Override
    public TrafficPolicyDetailResponse createTrafficPolicyCreate(TrafficRequest trafficRequest) {

        UserResponse userResponse = userClient.getUserForFeign(trafficRequest.userId());
        CustomerResponse customerResponse = customerClient.CustomerForFeign(trafficRequest.customerId());
        CarResponse carResponse = vehicleClient.getCarById(trafficRequest.carId());

        Double prim = calculateInsuranceValue(carResponse.carValue());

        CreateTrafficPolicyRequest request = new CreateTrafficPolicyRequest(
                prim,
                customerResponse.customerNumber(),
                "310",
                "T",
                15,
                LocalDate.now(),
                calculateFinishDate(LocalDate.now()),
                customerResponse.id(),
                userResponse.id()

                );

        PolicyResponse policyResponse = policyClient.createPolicy(request);

        TrafficPolicyCars trafficPolicyCars = new TrafficPolicyCars();
        trafficPolicyCars.setCarId(carResponse.id());
        trafficPolicyCars.setPolicyId(policyResponse.id());
        trafficPolicyCars.setCustomerId(customerResponse.id());

        TrafficPolicyCars toSave = trafficRepository.save(trafficPolicyCars);

        TrafficResponse trafficResponse = toResponse(toSave);

        TrafficPolicyDetailResponse trafficPolicyDetailResponse = new TrafficPolicyDetailResponse(policyResponse,customerResponse,userResponse,trafficResponse);


        return trafficPolicyDetailResponse;
    }

    @Override
    public List<TrafficResponse> getAllTrafficPolicyCars() {
        return null;
    }
    private Double calculateInsuranceValue(Double value){
        value = value*0.021;
        return value;
    }


    private LocalDate calculateFinishDate(LocalDate startDate) {
        return startDate.plusDays(15);
    }
    private TrafficResponse toResponse(TrafficPolicyCars trafficPolicyCars){
        TrafficResponse trafficResponse = new TrafficResponse(
                trafficPolicyCars.getId(),
                trafficPolicyCars.getPolicyId(),
                trafficPolicyCars.getCustomerId(),
                trafficPolicyCars.getCarId()
        );
        return trafficResponse;
    }
}
