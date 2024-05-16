package com.wordyarc.bookmaster.repository;

import com.wordyarc.bookmaster.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
