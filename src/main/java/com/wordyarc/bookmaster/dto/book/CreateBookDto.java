package com.wordyarc.bookmaster.dto.book;

import java.util.*;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для добавления новой книги")
public class CreateBookDto {

    @Schema(description = "Название книги", example = "Чистая архитектура")
    private String title;

    @Schema(description = "Международный стандартный книжный номер", example = "")
    private String isbn;

    @Schema(description = "Дата публикации", example = "")
    private Date publicationDate;

    @Schema(description = "id авторов", example = "")
    private Set<Long> authorIds;

}
