package com.wordyarc.bookmaster.controller;

import java.util.*;

import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.exception.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;
import org.springframework.web.*;

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
    ResponseEntity<CreatedDto> addBook(@RequestBody BookDto bookDto);

    @Operation(summary = "Изменение информации о книге включая авторов")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Книга успешно обновлена"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Книга не найдена",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    ResponseEntity<BookDto> updateBook(Long id, @RequestBody BookDto bookDTO);

    @Operation(summary = "Удаление книги")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Книга успешно удалена"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Книга не найдена",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    ResponseEntity<Void> deleteBook(Long id);

    @Operation(summary = "Получение списка книг с фильтрацией по наименованию, ISBN, автору")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Список книг успешно получен"
        )
    })
    ResponseEntity<List<BookDto>> getBooks(
        String title,
        String isbn,
        String author
    );

}
