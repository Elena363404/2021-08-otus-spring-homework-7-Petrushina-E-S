package ru.otus.elena363404.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> findById(long id);

  List<Comment> findAll();

  @Query("select c from Comment c where c.book = :book")
  List<Comment> findByBook(@Param("book") Book book);
}
