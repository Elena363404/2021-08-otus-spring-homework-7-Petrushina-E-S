package ru.otus.elena363404.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("Test Book")
class BookTest {

  @MockBean
  Shell shell;

  @DisplayName("Create Book by constructor")
  @Test
  void shouldHaveCorrectConstructor() {
    Book book = new Book(4, "It", new Author(3, "King"), new Genre(2, "Horror"));

    assertEquals(4, book.getId());
    assertEquals("It", book.getName());
    assertEquals("King", book.getAuthor().getName());
    assertEquals("Horror", book.getGenre().getName());
  }
}