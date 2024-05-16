package com.wordyarc.bookmaster.controller;

import java.util.*;

import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.author.*;
import com.wordyarc.bookmaster.dto.exception.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;
import org.springframework.web.*;

@Tag(name = "Авторы", description = "Добавление, получение, обновление и удаление авторов")
public interface AuthorController {

    @Operation(summary = "Добавление нового автора")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Автор успешно добавлен"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Проверьте правильность заполнения полей",
            content = @Content(schema = @Schema(implementation = InvalidFieldsDto.class))
        )
    })
    ResponseEntity<CreatedDto> addAuthor(@RequestBody CreateAuthorDto createAuthorDto);

    @Operation(summary = "Изменение информации об авторе")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Автор успешно обновлен"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Автор не найден",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    ResponseEntity<AuthorDto> updateAuthor(
        @Parameter(description = "Идентификатор автора", required = true)  Long id,
        @RequestBody CreateAuthorDto createAuthorDto
    );

    @Operation(summary = "Удаление автора")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Автор успешно удален"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Автор не найден",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    ResponseEntity<Void> deleteAuthor(@Parameter(description = "Идентификатор автора", required = true) Long id);

    @Operation(summary = "Получение списка авторов")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список авторов успешно получен")
    })
    ResponseEntity<List<AuthorDto>> getAuthors();
}
