package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class BookRepositoryTest {

  private static final long EXISTING_BOOK_ID = 3L;
  private static final int EXPECTED_NUMBER_OF_BOOKS = 5;
  private static final long BOOK_ID_FOR_DELETE = 2;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Add book in the DB")
  @Test
  void shouldInsertBook() {
    Book expectedBook = new Book(6,"BookForTest", new Author(2,"Alexander Pushkin"), new Genre(3,"Novel"));
    bookRepository.save(expectedBook);
    Book actualBook = em.find(Book.class, expectedBook.getId());
    assertThat(actualBook).isEqualTo(expectedBook);
  }

  @DisplayName("Return book by ID")
  @Test
  void shouldReturnExpectedBookById() {
    val optionalActualBook = bookRepository.findById(EXISTING_BOOK_ID);
    val expectedBook = em.find(Book.class, EXISTING_BOOK_ID);
    assertThat(optionalActualBook).isPresent().get().
      usingRecursiveComparison().isEqualTo(expectedBook);
  }

  @DisplayName("Delete book by ID")
  @Test
  void shouldCorrectDeleteBookById() {
    Book bookBeforeDelete = em.find(Book.class, BOOK_ID_FOR_DELETE);
    assertNotNull(bookBeforeDelete);
    bookRepository.deleteById(BOOK_ID_FOR_DELETE);
    em.flush();
    Book bookAfterDelete = em.find(Book.class, BOOK_ID_FOR_DELETE);
    assertNull(bookAfterDelete);
  }

  @DisplayName("Return list of the books")
  @Test
  void shouldReturnExpectedBooksList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val books = bookRepository.findAll();
    assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
      .allMatch(s -> !s.getName().equals(""))
      .allMatch(s -> s.getAuthor() != null)
      .allMatch(s -> s.getGenre() != null);
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }
}