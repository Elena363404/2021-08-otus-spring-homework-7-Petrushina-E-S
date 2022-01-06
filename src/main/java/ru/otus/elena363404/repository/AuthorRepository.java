package ru.otus.elena363404.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

  Optional<Author> findById(long id);

  List<Author> findAll();
}
