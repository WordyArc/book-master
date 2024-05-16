package com.wordyarc.bookmaster.controller.impl;

import java.util.*;

import com.wordyarc.bookmaster.controller.*;
import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.book.*;
import com.wordyarc.bookmaster.service.*;
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
    public ResponseEntity<CreatedDto> addBook(@RequestBody CreateBookDto createBookDto) {
        var createdDto = bookService.addBook(createBookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
        @PathVariable Long id,
        @RequestBody CreateBookDto createBookDto
    ) {
        var updatedBookDto = bookService.updateBook(id, createBookDto);

        return ResponseEntity.ok(updatedBookDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(String title, String isbn, String author) {
        return null;
    }

}
