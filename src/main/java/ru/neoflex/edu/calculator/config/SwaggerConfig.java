package ru.neoflex.edu.calculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vacation pay calculator API")
                        .description("Calculation of vacation pay for 12 months and the number of vacation days")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Roman Olontsev")
                                .email("rs.olontsev@gmail.com")));
    }
}
