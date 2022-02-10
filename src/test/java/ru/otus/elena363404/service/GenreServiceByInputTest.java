package ru.otus.elena363404.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.elena363404.domain.Genre;

import static org.mockito.Mockito.*;

@DisplayName("Test GenreServiceByInput")
@SpringBootTest(properties = {
  InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
  ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class GenreServiceByInputTest {

  @MockBean
  private IOService ioService;

  @Autowired
  private GenreService genreService;

  @MockBean
  private Shell shell;

  @Test
  @DisplayName("Check notification on create genre by input")
  void createGenre() {
    Genre expectedGenre = new Genre(5, "History");
    when(ioService.readString()).thenReturn("History");
    genreService.createGenre();
    verify(ioService, times(1)).out("Input name for the genre: \n");
  }

  @Test
  @DisplayName("Check notification on update genre by input")
  void updateGenre() {
    Genre expectedGenre = new Genre(1, "History");
    when(ioService.readString()).thenReturn("History");
    when(ioService.getInputId()).thenReturn(1L);
    genreService.updateGenre();
    verify(ioService, times(1)).out("Input id of the genre for update: \n");
    verify(ioService, times(1)).out("Input a new name for the genre: \n");
  }

  @Test
  @DisplayName("Check notification on delete genre")
  void deleteGenre() {
    when(ioService.getInputId()).thenReturn(5L);
    genreService.deleteGenre();
    verify(ioService, times(1)).out("Input id of the genre for delete: \n");
  }
}