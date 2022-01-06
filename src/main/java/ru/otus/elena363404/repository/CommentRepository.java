package ru.otus.elena363404.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByBook(Book book);
}
