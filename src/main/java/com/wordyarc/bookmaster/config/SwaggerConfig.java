package com.wordyarc.bookmaster.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("Book Master")
                    .description("Тестовое задание в ООО Конус")
                    .version("1.0.0")
            );
    }

}
