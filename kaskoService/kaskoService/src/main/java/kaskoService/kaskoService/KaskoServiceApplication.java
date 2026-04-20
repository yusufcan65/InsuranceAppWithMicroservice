package kaskoService.kaskoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KaskoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaskoServiceApplication.class, args);
	}

}
