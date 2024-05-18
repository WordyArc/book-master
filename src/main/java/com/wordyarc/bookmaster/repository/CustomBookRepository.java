package com.wordyarc.bookmaster.repository;

import java.util.*;

import com.wordyarc.bookmaster.model.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.*;

@Repository
public class CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> findBooksByFilters(String title, String isbn, String author) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> predicates = getPredicates(builder, root, title, isbn, author);
        query.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(query).getResultList();
    }

    private List<Predicate> getPredicates(
        CriteriaBuilder builder,
        Root<Book> root,
        String title,
        String isbn,
        String author
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (title != null && !title.isEmpty()) {
            predicates.add(
                builder.like(
                    builder.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
                )
            );
        }
        if (isbn != null && !isbn.isEmpty()) {
            predicates.add(builder.equal(root.get("isbn"), isbn));
        }
        if (author != null && !author.isEmpty()) {
            Join<Book, Author> authors = root.join("authors", JoinType.LEFT);
            predicates.add(
                builder.or(
                    builder.like(
                        builder.lower(authors.get("firstName")),
                        "%" + author.toLowerCase() + "%"),
                    builder.like(
                        builder.lower(authors.get("lastName")),
                        "%" + author.toLowerCase() + "%")
                )
            );
        }

        return predicates;
    }

}
