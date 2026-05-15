package insurance.kaskoService.Service.Impl;

import insurance.insuranceCommon.RestResponse;
import insurance.kaskoService.Client.CustomerClient;
import insurance.kaskoService.Client.PolicyClient;
import insurance.kaskoService.Client.UserClient;
import insurance.kaskoService.Client.VehicleClient;
import insurance.kaskoService.Dto.*;
import insurance.kaskoService.Entity.KaskoPolicyCars;
import insurance.kaskoService.Repository.KaskoRepository;
import insurance.kaskoService.Service.KaskoService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class KaskoServiceImpl implements KaskoService {

    private final PolicyClient policyClient;
    private final CustomerClient customerClient;
    private final UserClient userClient;
    private final KaskoRepository kaskoRepository;
    private final VehicleClient vehicleClient;

    public KaskoServiceImpl(PolicyClient policyClient, CustomerClient customerClient,
                              UserClient userClient, KaskoRepository kaskoRepository,
                              VehicleClient vehicleClient) {
        this.policyClient = policyClient;
        this.customerClient = customerClient;
        this.userClient = userClient;
        this.kaskoRepository = kaskoRepository;
        this.vehicleClient = vehicleClient;
    }

    @Override
    public KaskoPolicyDetailResponse createKaskoPolicyCreate(KaskoRequest kaskoRequest) {

        UserResponse userResponse = userClient.getUserForFeign(kaskoRequest.userId()).getData();

        RestResponse<CustomerResponse> customerResponse1 = customerClient.getCustomerForFeign(kaskoRequest.customerId());
        CustomerResponse customerResponse = customerResponse1.getData();

        CarResponse carResponse = vehicleClient.getCarById(kaskoRequest.carId()).getData();

        Double prim = calculateInsuranceValue(carResponse.carValue());

        CreateKaskoPolicyRequest request = new CreateKaskoPolicyRequest(
                prim,
                customerResponse.customerNumber(),
                "340",
                "T",
                15,
                LocalDate.now(),
                calculateFinishDate(LocalDate.now()),
                customerResponse.id(),
                userResponse.id()

                );

        RestResponse<PolicyResponse> policyResponse = policyClient.createPolicy(request);
        PolicyResponse policyResponse1 = policyResponse.getData();

        KaskoPolicyCars kaskoPolicyCars = new KaskoPolicyCars();
        kaskoPolicyCars.setCarId(carResponse.id());
        kaskoPolicyCars.setPolicyId(policyResponse1.id());
        kaskoPolicyCars.setCustomerId(customerResponse.id());

        KaskoPolicyCars toSave = kaskoRepository.save(kaskoPolicyCars);

        KaskoResponse kaskoResponse = toResponse(toSave);

        KaskoPolicyDetailResponse kaskoPolicyDetailResponse = new KaskoPolicyDetailResponse(policyResponse1,customerResponse,userResponse,kaskoResponse);


        return kaskoPolicyDetailResponse;
    }

    @Override
    public List<KaskoResponse> getAllKaskoPolicyCars() {
        return null;
    }
    public Double calculateInsuranceValue(Double value){
        value = value*0.021;
        return value;
    }


    private LocalDate calculateFinishDate(LocalDate startDate) {
        return startDate.plusDays(15);
    }
    private KaskoResponse toResponse(KaskoPolicyCars kaskoPolicyCars){
        KaskoResponse kaskoResponse = new KaskoResponse(
                kaskoPolicyCars.getId(),
                kaskoPolicyCars.getPolicyId(),
                kaskoPolicyCars.getCustomerId(),
                kaskoPolicyCars.getCarId()
        );
        return kaskoResponse;
    }
}
