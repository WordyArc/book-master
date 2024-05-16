package com.wordyarc.bookmaster.dto.exception;

import java.util.*;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ошибки, произошедшие при валидации полей")
public class InvalidFieldsDto {

    @Schema(
        description = "Описание ошибки",
        example = "Некоторые поля не прошли валидацию"
    )
    private String message;

    @Schema(
        description = "Список ошибок с описанием",
        example = """
                [
                    {
                        "field":"name",
                        "message":"Name is required"
                    },
                    {
                        "field":"age",
                        "message":"Age is required"
                    }
                ]
            """
    )
    private List<InvalidFieldDto> errors;

}
