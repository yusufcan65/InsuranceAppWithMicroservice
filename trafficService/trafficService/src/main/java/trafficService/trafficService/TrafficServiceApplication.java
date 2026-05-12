package trafficService.trafficService;

import insurance.insuranceCommon.Config.ObservationConfig;
import insurance.insuranceCommon.Config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import({SwaggerConfig.class, ObservationConfig.class})
public class TrafficServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficServiceApplication.class, args);
	}

}
