package ru.otus.elena363404.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.elena363404.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
