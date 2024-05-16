package com.wordyarc.bookmaster.controller.impl;

import java.util.*;

import com.wordyarc.bookmaster.controller.*;
import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.author.*;
import com.wordyarc.bookmaster.service.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book-service")
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    @PostMapping
    public ResponseEntity<CreatedDto> addAuthor(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        var createdDto = authorService.addAuthor(createAuthorDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(
        @Valid @PathVariable Long id,
        @Valid @RequestBody CreateAuthorDto createAuthorDto
    ) {
        var updatedAuthorDTO = authorService.updateAuthor(id, createAuthorDto);

        return ResponseEntity.ok(updatedAuthorDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(
        @Valid @PathVariable Long id
    ) {
        authorService.deleteAuthorById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        var authorDtos = authorService.getAuthors();

        return ResponseEntity.ok().body(authorDtos);
    }

}
