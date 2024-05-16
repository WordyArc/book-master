package com.wordyarc.bookmaster.dto;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Schema(description = "Успешный ответ после создания сущности")
public class CreatedDto {

    @Schema(
        description = "ID Сущности",
        example = "4"
    )
    private final Long id;

}
