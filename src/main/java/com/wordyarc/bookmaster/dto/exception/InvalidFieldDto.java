package com.wordyarc.bookmaster.dto.exception;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Поле не прошедшее валидацию + описание ошибки")
public class InvalidFieldDto {

    @Schema(
        description = "Название поля",
        example = "name"
    )
    private String field;

    @Schema(
        description = "Информация об ошибке",
        example = "Имя покемона обязательно"
    )
    private String message;

}
