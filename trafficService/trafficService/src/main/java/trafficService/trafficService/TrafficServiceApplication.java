package trafficService.trafficService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TrafficServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficServiceApplication.class, args);
	}

}
