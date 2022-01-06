package ru.otus.elena363404.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

  Optional<Book> findById(long id);

  @EntityGraph(attributePaths = {"author", "genre"})
  List<Book> findAll();

}
