package com.wordyarc.bookmaster.controller;

import java.util.*;

import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.book.*;
import com.wordyarc.bookmaster.dto.exception.*;
import com.wordyarc.bookmaster.exception.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;

@Tag(
    name = "Книги",
    description = "Добавление, получение, обновление и удаление книг"
)
public interface BookController {

    @Operation(summary = "Добавление новой книги")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Книга успешно добавлена"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Проверьте правильность заполнения полей",
            content = @Content(schema = @Schema(implementation = InvalidFieldsDto.class))
        )
    })
    ResponseEntity<CreatedDto> addBook(@RequestBody CreateBookDto createBookDto);

    @Operation(summary = "Изменение информации о книге включая авторов")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Книга успешно обновлена"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Книга не найдена",
            content = @Content(schema = @Schema(implementation = ApiException.class))
        )
    })
    ResponseEntity<BookDto> updateBook(
        @Parameter(description = "Идентификатор книги", required = true)  Long id,
        @RequestBody CreateBookDto createBookDto
    );

    @Operation(summary = "Удаление книги")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Книга успешно удалена"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Книга не найдена",
            content = @Content(schema = @Schema(implementation = ApiException.class))
        )
    })
    ResponseEntity<Void> deleteBook(@Parameter(description = "Идентификатор книги", required = true)  Long id);

    @Operation(summary = "Получение списка книг с фильтрацией по наименованию, ISBN, автору")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Список книг успешно получен"
        )
    })
    ResponseEntity<List<BookDto>> getBooks(
        @Parameter(description = "Название книги") String title,
        @Parameter(description = "ISBN книги") String isbn,
        @Parameter(description = "Имя автора книги") String author
    );

}
