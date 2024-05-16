package com.wordyarc.bookmaster.repository;

import java.util.*;

import com.wordyarc.bookmaster.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByIdIn(Set<Long> ids);
}
