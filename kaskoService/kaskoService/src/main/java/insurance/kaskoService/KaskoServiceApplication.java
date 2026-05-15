package insurance.kaskoService;

import insurance.insuranceCommon.Config.ObservationConfig;
import insurance.insuranceCommon.Config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import({SwaggerConfig.class, ObservationConfig.class})
public class KaskoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaskoServiceApplication.class, args);
	}

}
