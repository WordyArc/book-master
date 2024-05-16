package com.wordyarc.bookmaster.service;

import java.util.*;

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

    private Set<Author> findAuthorsByIds(Set<Long> authorIds) {
        List<Author> authors = authorRepository.findByIdIn(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new NotFoundException("Один или несколько авторов не найдено по id");
        }

        return new HashSet<>(authors);
    }

}
