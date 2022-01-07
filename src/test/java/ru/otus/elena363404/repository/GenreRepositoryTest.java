package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.elena363404.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
class GenreRepositoryTest {

  private static final long EXISTING_GENRE_ID = 3L;
  private static final long GENRE_ID_FOR_DELETE = 5;
  private static final int EXPECTES_NUMBER_OF_GENRES = 5;

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Add genre in the DB")
  @Test
  void shouldInsertGenre() {
    Genre expectedGenre = new Genre(5, "Thriller");
    genreRepository.save(expectedGenre);
    Genre actualGenre = em.find(Genre.class, expectedGenre.getId());
    assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
  }

  @DisplayName("Return genre by ID")
  @Test
  void shouldReturnExpectedGenreById() {
    val optionalActualGenre = genreRepository.findById(EXISTING_GENRE_ID);
    val expectedGenre = em.find(Genre.class, EXISTING_GENRE_ID);
    assertThat(optionalActualGenre).isPresent().get().
      usingRecursiveComparison().isEqualTo(expectedGenre);
  }

  @DisplayName("Delete genre by ID")
  @Test
  void shouldCorrectDeleteGenreById() {
    Genre genreBeforeDelete = em.find(Genre.class, GENRE_ID_FOR_DELETE);
    genreRepository.deleteById(GENRE_ID_FOR_DELETE);
    em.flush();
    Genre genreAfterDelete = em.find(Genre.class, GENRE_ID_FOR_DELETE);
    assertNull(genreAfterDelete);
  }

  @DisplayName("Return list of the genres")
  @Test
  void shouldReturnExpectedGenresList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val genres = genreRepository.findAll();
    assertThat(genres).isNotNull().hasSize(EXPECTES_NUMBER_OF_GENRES)
      .allMatch(s -> !s.getName().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }

}