package insurance.healthService.Service.Impl;

import insurance.healthService.Client.CustomerClient;
import insurance.healthService.Client.PolicyClient;
import insurance.healthService.Client.UserClient;
import insurance.healthService.Dto.*;
import insurance.healthService.Entity.Health;
import insurance.healthService.Repository.HealthRepository;
import insurance.healthService.Service.HealthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthServiceImpl implements HealthService {

    private final HealthRepository healthRepository;
    private final PolicyClient policyClient;
    private final CustomerClient customerClient;
    private final UserClient userClient;

    public HealthServiceImpl(HealthRepository healthRepository, PolicyClient policyClient,
                             CustomerClient customerClient, UserClient userClient) {
        this.healthRepository = healthRepository;
        this.policyClient = policyClient;
        this.customerClient = customerClient;
        this.userClient = userClient;
    }

    @Override
    public HealthPolicyResponse createHealthPolicy(HealthRequest request) {

        UserResponse userResponse = userClient.getUserForFeign(request.userId());
        CustomerResponse customerResponse = customerClient.CustomerForFeign(request.customerId());

        Double prim = calculateInsuranceValue(customerResponse.birthDate(), request.smokeStatus(),
                request.sporStatus(), request.operationStatus(), request.chronicDiseaseStatus());

        CreateHealthPolicyRequest policyRequest = new CreateHealthPolicyRequest(
                prim,
                customerResponse.customerNumber(),
                "610",
                "T",
                15,
                LocalDate.now(),
                calculateFinishDate(LocalDate.now()),
                customerResponse.id(),
                userResponse.id()
        );

        PolicyResponse policyResponse = policyClient.createPolicy(policyRequest);

        int age = calculateAgeCustomer(customerResponse.birthDate());
        Health health = new Health();
        health.setAge(age);
        health.setPolicyId(policyResponse.id());
        health.setCustomerId(customerResponse.id());
        health.setSmokeStatus(request.smokeStatus());
        health.setOperationStatus(request.operationStatus());
        health.setChronicDiseaseStatus(request.chronicDiseaseStatus());
        health.setSporStatus(request.sporStatus());

        Health toSave = healthRepository.save(health);

        HealthResponse healthResponse = toResponse(toSave);

        HealthPolicyResponse healthPolicyResponse = new HealthPolicyResponse(healthResponse,customerResponse,userResponse,policyResponse);

        return healthPolicyResponse;
    }

    @Override
    public List<HealthResponse> getAllHealths() {
        List<Health>  healthList = healthRepository.findAll();

        return toResponseList(healthList);
    }

    public int calculateAgeCustomer(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();

        // Doğrudan yaşı hesaplar
        Period period = Period.between(birthDate, currentDate);

        int age = period.getYears();

        return age;
    }
    private LocalDate calculateFinishDate(LocalDate startDate) {
        return startDate.plusDays(15);
    }



    public double calculateInsuranceValue(LocalDate birthDate, String smokeStatus,
                                          String sporStatus, String operationStatus,
                                          String chronicDiseaseStatus){

        int age = calculateAgeCustomer(birthDate);
        Double primValue = 1000.0;

        // Yaş faktörü
        if (age < 30) {
            primValue *= 1.1;
        } else if (age < 50) {
            primValue *= 1.3;
        } else {
            primValue *= 1.5;
        }

        // Sigara içme durumu
        if ("evet".equalsIgnoreCase(smokeStatus)) {
            primValue *= 1.7;
        } else {
            primValue *= 1.1;
        }

        // Spor yapma durumu
        if ("hayır".equalsIgnoreCase(sporStatus)) {
            primValue *= 1.3;
        } else {
            primValue *= 0.95;
        }

        // Geçmiş operasyon durumu
        if ("evet".equalsIgnoreCase(operationStatus)) {
            primValue *= 1.5;
        } else {
            primValue *= 1.05;
        }

        // Kronik hastalık durumu
        if ("evet".equalsIgnoreCase(chronicDiseaseStatus)) {
            primValue *= 1.8;
        } else {
            primValue *= 1.063;
        }

        return primValue;
    }


    private HealthResponse toResponse( Health health){
        HealthResponse healthResponse = new HealthResponse(
                health.getId(),
                health.getAge(),
                health.getSmokeStatus(),
                health.getSporStatus(),
                health.getOperationStatus(),
                health.getChronicDiseaseStatus(),
                health.getPolicyId(),
                health.getCustomerId()
        );
        return healthResponse;
    }

    private List<HealthResponse> toResponseList(List<Health> healthList){
        List<HealthResponse> healthResponses = healthList.stream().map(this::toResponse).collect(Collectors.toList());
        return healthResponses;
    }
}
