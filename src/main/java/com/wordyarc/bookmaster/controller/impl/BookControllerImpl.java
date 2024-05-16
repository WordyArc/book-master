package com.wordyarc.bookmaster.controller.impl;

import java.util.*;

import com.wordyarc.bookmaster.controller.*;
import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.book.*;
import com.wordyarc.bookmaster.service.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author-service")
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    @PostMapping
    public ResponseEntity<CreatedDto> addBook(@Valid @RequestBody CreateBookDto createBookDto) {
        var createdDto = bookService.addBook(createBookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
        @Valid @PathVariable Long id,
        @Valid @RequestBody CreateBookDto createBookDto
    ) {
        var updatedBookDto = bookService.updateBook(id, createBookDto);

        return ResponseEntity.ok(updatedBookDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Valid @PathVariable Long id) {
        bookService.deleteBookById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String isbn,
        @RequestParam(required = false) String author
    ) {
        List<BookDto> bookDTOs = bookService.getBooks(title, isbn, author);

        return ResponseEntity.ok(bookDTOs);
    }

}
