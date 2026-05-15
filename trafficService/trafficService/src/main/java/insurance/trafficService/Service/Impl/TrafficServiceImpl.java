package insurance.trafficService.Service.Impl;

import insurance.insuranceCommon.RestResponse;
import insurance.trafficService.Client.CustomerClient;
import insurance.trafficService.Dto.*;
import insurance.trafficService.Repository.TrafficRepository;
import org.springframework.stereotype.Service;
import insurance.trafficService.Client.PolicyClient;
import insurance.trafficService.Client.UserClient;
import insurance.trafficService.Client.VehicleClient;
import insurance.trafficService.Entity.TrafficPolicyCars;
import insurance.trafficService.Service.TrafficService;

import java.time.LocalDate;
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

        UserResponse userResponse = userClient.getUserForFeign(trafficRequest.userId()).getData();

        RestResponse<CustomerResponse> customerResponse1 = customerClient.getCustomerForFeign(trafficRequest.customerId());
        CustomerResponse customerResponse = customerResponse1.getData();

        RestResponse<CarResponse> carResponse = vehicleClient.getCarById(trafficRequest.carId());
        CarResponse carResponse1= carResponse.getData();

        Double prim = calculateInsuranceValue(carResponse1.carValue());

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

        RestResponse<PolicyResponse> policyResponse = policyClient.createPolicy(request);
        PolicyResponse policyResponse1 = policyResponse.getData();

        TrafficPolicyCars trafficPolicyCars = new TrafficPolicyCars();
        trafficPolicyCars.setCarId(carResponse1.id());
        trafficPolicyCars.setPolicyId(policyResponse1.id());
        trafficPolicyCars.setCustomerId(customerResponse.id());

        TrafficPolicyCars toSave = trafficRepository.save(trafficPolicyCars);

        TrafficResponse trafficResponse = toResponse(toSave);

        TrafficPolicyDetailResponse trafficPolicyDetailResponse = new TrafficPolicyDetailResponse(policyResponse1,customerResponse,userResponse,trafficResponse);


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
