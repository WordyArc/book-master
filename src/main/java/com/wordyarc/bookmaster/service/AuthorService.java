package com.wordyarc.bookmaster.service;

import com.wordyarc.bookmaster.dto.*;
import com.wordyarc.bookmaster.dto.author.*;
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

}
