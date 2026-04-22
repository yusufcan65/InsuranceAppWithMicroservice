package kaskoService.kaskoService.Service.Impl;

import kaskoService.kaskoService.Client.CustomerClient;
import kaskoService.kaskoService.Client.PolicyClient;
import kaskoService.kaskoService.Client.UserClient;
import kaskoService.kaskoService.Client.VehicleClient;
import kaskoService.kaskoService.Dto.*;
import kaskoService.kaskoService.Entity.KaskoPolicyCars;
import kaskoService.kaskoService.Repository.KaskoRepository;
import kaskoService.kaskoService.Service.KaskoService;
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

        UserResponse userResponse = userClient.getUserForFeign(kaskoRequest.userId());
        CustomerResponse customerResponse = customerClient.getCustomerForFeign(kaskoRequest.customerId());
        CarResponse carResponse = vehicleClient.getCarById(kaskoRequest.carId());

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

        PolicyResponse policyResponse = policyClient.createPolicy(request);

        KaskoPolicyCars kaskoPolicyCars = new KaskoPolicyCars();
        kaskoPolicyCars.setCarId(carResponse.id());
        kaskoPolicyCars.setPolicyId(policyResponse.id());
        kaskoPolicyCars.setCustomerId(customerResponse.id());

        KaskoPolicyCars toSave = kaskoRepository.save(kaskoPolicyCars);

        KaskoResponse kaskoResponse = toResponse(toSave);

        KaskoPolicyDetailResponse kaskoPolicyDetailResponse = new KaskoPolicyDetailResponse(policyResponse,customerResponse,userResponse,kaskoResponse);


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
