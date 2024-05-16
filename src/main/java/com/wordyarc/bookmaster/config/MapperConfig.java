package com.wordyarc.bookmaster.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import org.modelmapper.*;
import org.springframework.context.annotation.*;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JsonMapper jsonMapper() {
        return new JsonMapper();
    }

    @Bean
    public ObjectMapper objectMapper(ObjectMapper objectMapper) {
        return objectMapper.registerModule(new JavaTimeModule());
    }

}
