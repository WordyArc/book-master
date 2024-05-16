package com.wordyarc.bookmaster.service;

import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.author.*;
import com.wordyarc.bookmaster.exception.*;
import com.wordyarc.bookmaster.model.*;
import com.wordyarc.bookmaster.repository.*;
import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final ModelMapper mapper;

    public CreatedDto addAuthor(CreateAuthorDto dto) {
        var author = mapper.map(dto, Author.class);
        var createdAuthor = authorRepository.save(author);

        return new CreatedDto(createdAuthor.getId());
    }

    public AuthorDto updateAuthor(Long id, CreateAuthorDto dto) {
        var author = authorRepository.findById(id).orElseThrow(AuthorService::getAuthorNotFoundException);
        mapper.map(dto, author);
        var updatedAuthor = authorRepository.save(author);

        return mapper.map(updatedAuthor, AuthorDto.class);
    }

    private static NotFoundException getAuthorNotFoundException() {
        return new NotFoundException("Автор не найден");
    }

}
