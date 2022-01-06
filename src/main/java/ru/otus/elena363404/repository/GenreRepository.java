package ru.otus.elena363404.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

  Optional<Genre> findById(long id);

  List<Genre> findAll();
}
