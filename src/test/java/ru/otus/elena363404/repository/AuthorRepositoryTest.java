package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.elena363404.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

  private static final long EXISTING_AUTHOR_ID = 3L;
  private static final int EXPECTES_NUMBER_OF_AUTHORS = 5;
  private static final long AUTHOR_ID_FOR_DELETE = 5;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Add author in the DB")
  @Test
  void shouldInsertAuthor() {
    Author expectedAuthor = new Author(5,"Lermontov");
    authorRepository.save(expectedAuthor);
    Author actualAuthor = em.find(Author.class, expectedAuthor.getId());
    assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
  }

  @DisplayName("Return author by ID")
  @Test
  void shouldReturnExpectedAuthorById() {
    val optionalActualAuthor = authorRepository.findById(EXISTING_AUTHOR_ID);
    val expectedAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
    assertThat(optionalActualAuthor).isPresent().get().
      usingRecursiveComparison().isEqualTo(expectedAuthor);
  }

  @DisplayName("Delete author by ID")
  @Test
  void shouldCorrectDeleteAuthorById() {
    Author authorBeforeDelete = em.find(Author.class, AUTHOR_ID_FOR_DELETE);
    assertNotNull(authorBeforeDelete);
    authorRepository.deleteById(AUTHOR_ID_FOR_DELETE);
    em.flush();
    Author authorAfterDelete = em.find(Author.class, AUTHOR_ID_FOR_DELETE);
    assertNull(authorAfterDelete);
  }

  @DisplayName("Return list of the authors")
  @Test
  void shouldReturnExpectedAuthorsList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val authors = authorRepository.findAll();
    assertThat(authors).isNotNull().hasSize(EXPECTES_NUMBER_OF_AUTHORS)
      .allMatch(s -> !s.getName().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }
}