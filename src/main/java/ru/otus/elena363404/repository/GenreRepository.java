package ru.otus.elena363404.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.elena363404.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
