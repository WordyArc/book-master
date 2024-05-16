package com.wordyarc.bookmaster.dto.author;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO Создания нового автора")
public class CreateAuthorDto {

    @Schema(description = "Имя", example = "Алексей")
    private String firstName;

    @Schema(description = "Фамилия", example = "Попов")
    private String lastName;

}
