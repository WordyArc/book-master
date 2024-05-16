package com.wordyarc.bookmaster.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("Book Master API")
                    .version("1.0.0")
                    .description("Тестовое задание в ООО Конус")
            );
    }

}
