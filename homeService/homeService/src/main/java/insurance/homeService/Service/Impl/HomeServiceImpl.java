package insurance.homeService.Service.Impl;

import insurance.homeService.Client.CustomerClient;
import insurance.homeService.Client.PolicyClient;
import insurance.homeService.Client.UserClient;
import insurance.homeService.Dto.*;
import insurance.homeService.Entity.Home;
import insurance.homeService.Repository.HomeRepository;
import insurance.homeService.Service.HomeService;
import insurance.insuranceCommon.RestResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {


    private final HomeRepository homeRepository;
    private final PolicyClient policyClient;
    private final CustomerClient customerClient;
    private final UserClient userClient;

    public HomeServiceImpl(HomeRepository homeRepository, PolicyClient policyClient,
                           CustomerClient customerClient, UserClient userClient) {
        this.homeRepository = homeRepository;
        this.policyClient = policyClient;
        this.customerClient = customerClient;
        this.userClient = userClient;
    }

    @Override
    public DaskResponse createDaskPolicy(DaskRequest daskRequest) {

        UserResponse user = userClient.getUserForFeign(daskRequest.userId());
        CustomerResponse customer = customerClient.getCustomerForFeign(daskRequest.customerId());

        Double prim = calculateInsuranceValue(daskRequest.squareMeter(),daskRequest.floorNumber(),
                daskRequest.numberBuildFloor(), daskRequest.damageState(), daskRequest.buildingAge());


        CreateDaskPolicyRequest policyRequest = new CreateDaskPolicyRequest(
                prim,
                customer.customerNumber(),
                "199",
                "T",
                15,
                LocalDate.now(),
                calculateFinishDate(LocalDate.now()),
                customer.id(),
                user.id()
        );
        RestResponse<PolicyResponse> policyResponse = policyClient.createPolicy(policyRequest);
        PolicyResponse policyResponse1 = policyResponse.getData();

        Home home = new Home();
        home.setAddressCode(daskRequest.addressCode());
        home.setPolicyId(policyResponse1.id());
        home.setBuildingAge(daskRequest.buildingAge());
        home.setBuildStyle(daskRequest.BuildStyle());
        home.setDamageState(daskRequest.damageState());
        home.setSquareMeter(daskRequest.squareMeter());
        home.setFloorNumber(daskRequest.floorNumber());
        home.setCustomerId(customer.id());
        home.setNumberBuildFloor(daskRequest.numberBuildFloor());

        Home toSave = homeRepository.save(home);

        HomeResponse  homeResponse = toResponse(toSave);

        DaskResponse daskResponse = new DaskResponse(homeResponse,policyResponse1,customer,user);

        return daskResponse;
    }

    public Double calculateInsuranceValue(Double squareMeter, int floorNumber,int numberBuildFloor,String damageState, int buildingAge){

        Double baseValue = squareMeter * 27; // Metrekare başına bir değer

        // Kat numarasına göre düzenleme
        if (floorNumber <= 2) {
            baseValue += 500; // Alt katlar daha riskli
        } else if (floorNumber > 5 && floorNumber <10) {
            baseValue += 300; // Üst katlar daha az riskli
        } else if (floorNumber> 15) {
            baseValue +=100;
        }

        // Bina kat sayısına göre düzenleme
        if (numberBuildFloor < 5) {
            baseValue += 500; // Yüksek binalar daha riskli
        } else if (numberBuildFloor > 5 && numberBuildFloor < 10 ) {
            baseValue += 750;
        } else if (numberBuildFloor > 15) {
            baseValue += 1000;
        }

        // Hasar durumuna göre düzenleme
        if (damageState.equalsIgnoreCase("Hasarsız")) {
            baseValue *= 1.1; // Hasarlı binalar için bedel %50 artar
        } else if (damageState.equalsIgnoreCase("Az Hasarlı")) {
            baseValue *= 1.3; // Hasarsız binalar için bedel %10 düşer
        }
        else if (damageState.equalsIgnoreCase("Orta Hasarlı")) {
            baseValue *= 1.5; // Hasarsız binalar için bedel %10 düşer
        }
        else if (damageState.equalsIgnoreCase("Ağır Hasarlı")) {
            baseValue *= 1.7; // Hasarsız binalar için bedel %10 düşer
        }

        // Yapılış yılına göre düzenleme
        baseValue =baseValue + buildingAge*1.2;

        // Son değer
        return baseValue;


    }

    private LocalDate calculateFinishDate(LocalDate startDate) {
        return startDate.plusDays(15);
    }
    @Override
    public List<HomeResponse> getAllHomes() {
        return null;
    }

    private HomeResponse toResponse(Home home){
        HomeResponse homeResponse = new HomeResponse(
                home.getPolicyId(),
                home.getCustomerId(),
                home.getId(),
                home.getAddressCode(),
                home.getSquareMeter(),
                home.getFloorNumber(),
                home.getBuildStyle(),
                home.getNumberBuildFloor(),
                home.getDamageState(),
                home.getBuildingAge()
        );
        return homeResponse;
    }


}
