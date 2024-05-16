package com.wordyarc.bookmaster.dto.book;

import java.util.*;

import com.wordyarc.bookmaster.dto.author.*;
import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO Книги")
public class BookDto {

    @Schema(description = "id книги", example = "1")
    private Long id;

    @Schema(description = "Название книги", example = "Чистый код")
    private String title;

    @Schema(description = "Международный стандартный книжный номер", example = "")
    private String isbn;

    @Schema(description = "Дата публикации", example = "")
    private Date publicationDate;

    @Schema(description = "Список авторов", example = "")
    private Set<AuthorDto> authors;

}
