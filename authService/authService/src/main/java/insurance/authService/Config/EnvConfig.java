package insurance.authService.Config;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class EnvConfig {

    @PostConstruct
    public void init() {
        // .env dosyasını yükle
        Dotenv dotenv = Dotenv.configure()
                .directory("./") // Kök dizine bakar
                .ignoreIfMissing() // Dosya yoksa hata verme (Docker için önemli)
                .load();

        // Her bir değişkeni System Property olarak set et
        dotenv.entries().forEach(entry -> {
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }
}