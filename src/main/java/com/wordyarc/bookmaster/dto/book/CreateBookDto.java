package com.wordyarc.bookmaster.dto.book;

import java.util.*;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для добавления новой книги")
public class CreateBookDto {

    @Schema(description = "Название книги", example = "Чистая архитектура")
    @NotBlank
    private String title;

    @Schema(description = "Международный стандартный книжный номер", example = "9783161484100")
    @NotBlank
    @Size(max = 13)
    private String isbn;

    @Schema(description = "Дата публикации", example = "2024-05-16T17:30:25.875Z")
    @PastOrPresent(message = "Дата публикации не может быть в будущем")
    private Date publicationDate;

    @Schema(description = "id авторов", example = "[1, 2, 3]")
    @NotEmpty(message = "ID авторов не должны быть пустыми")
    @Size(min = 1, message = "Должен быть хотя бы один автор")
    private Set<Long> authorIds;

}
