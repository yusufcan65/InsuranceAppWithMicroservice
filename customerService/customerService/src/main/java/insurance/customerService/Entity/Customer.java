package insurance.customerService.Entity;


import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Customer extends BaseEntity {

    private String name;
    private String surname;

    private String idNumber;

    private LocalDate birthDate;

    private String city;
    private String district;

    private String phoneNumber;
    private String email;

    private Integer customerNumber;

    private UUID userId; // relationship yerine
}