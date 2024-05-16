package com.wordyarc.bookmaster.service;

import java.util.*;
import java.util.stream.*;

import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.book.*;
import com.wordyarc.bookmaster.exception.*;
import com.wordyarc.bookmaster.model.*;
import com.wordyarc.bookmaster.repository.*;
import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final ModelMapper mapper;

    @Transactional
    public CreatedDto addBook(CreateBookDto dto) {
        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new BadRequestException("ISBN Должен быть уникален");
        }

        var book = mapper.map(dto, Book.class);
        book.setAuthors(findAuthorsByIds(dto.getAuthorIds()));
        var createdBook = bookRepository.save(book);

        return new CreatedDto(createdBook.getId());
    }

    public BookDto updateBook(Long id, CreateBookDto createBookDto) {
        var book = bookRepository.findById(id)
            .orElseThrow(BookService::getBookNotFoundException);
        if (!book.getIsbn().equals(createBookDto.getIsbn()) && bookRepository.existsByIsbn(createBookDto.getIsbn())) {
            throw new BadRequestException("ISBN должен быть уникален");
        }
        mapper.map(createBookDto, book);
        book.setAuthors(findAuthorsByIds(createBookDto.getAuthorIds()));
        var updatedBook = bookRepository.save(book);

        return convertToDto(updatedBook);
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw getBookNotFoundException();
        }

        bookRepository.deleteById(id);
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = mapper.map(book, BookDto.class);
        bookDto.setAuthorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()));

        return bookDto;
    }

    private Set<Author> findAuthorsByIds(Set<Long> authorIds) {
        List<Author> authors = authorRepository.findByIdIn(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new NotFoundException("Один или несколько авторов не найдено по id");
        }

        return new HashSet<>(authors);
    }

    public static NotFoundException getBookNotFoundException() {
        return new NotFoundException("Книга не найдена");
    }
}
