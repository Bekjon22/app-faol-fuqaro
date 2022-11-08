package uz.softex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@OpenAPIDefinition(info = @Info(title = "Faol-Fuqaro API", version = "2.0", description = "Faol Fuqaro"))
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer",bearerFormat = "JWT",type = SecuritySchemeType.HTTP)
@SpringBootApplication
public class AppFaolFuqaroServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppFaolFuqaroServerApplication.class, args);


    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
