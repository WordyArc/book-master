package com.wordyarc.bookmaster.dto;

import java.util.*;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO Автора")
public class AuthorDto {

    @Schema(description = "id автора", example = "1")
    private Long id;

    @Schema(description = "Имя", example = "Алексей")
    private String firstName;

    @Schema(description = "Фамилия", example = "Попов")
    private String lastName;

    @Schema(description = "Список книг автора", example = "")
    private Set<BookDto> books;

}
