package ru.otus.elena363404.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("Test CommentServiceByInput")
@SpringBootTest(properties = {
  InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
  ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class CommentServiceByInputTest {

  @MockBean
  private IOService ioService;

  @Autowired
  private CommentService commentService;

  @MockBean
  private Shell shell;

  @Test
  @DisplayName("Check notification on create comment by input")
  void createCommentTest() {
    when(ioService.readString()).thenReturn("Comment for book");
    when(ioService.getInputId()).thenReturn(2L);
    commentService.createComment();
    verify(ioService, times(1)).out("Input comment: \n");
  }

  @Test
  @DisplayName("Check notification on update comment by input")
  void updateCommentTest() {
    when(ioService.readString()).thenReturn("Good book");
    when(ioService.getInputId()).thenReturn(1L);
    commentService.updateComment();
    verify(ioService, times(1)).out("Input id of the comment for update: \n");
    verify(ioService, times(1)).out("Input a new comment: \n");
  }

  @Test
  @DisplayName("Check notification on delete comment")
  void deleteCommentTest() {
    when(ioService.getInputId()).thenReturn(1L);
    commentService.deleteComment();
    verify(ioService, times(1)).out("Input id of the comment for delete: \n");
    when(ioService.getInputId()).thenReturn(1L);
  }

  @Test
  @DisplayName("Check notification on get comment by ID")
  void getCommentByIdTest() {
    commentService.getCommentById();
    verify(ioService, times(1)).out("Input id of the comment: \n");
    when(ioService.getInputId()).thenReturn(1L);
  }

  private List<Book> getAllBooks() {
    List<Book> bookList = new ArrayList<>();
    List<Author> authorList = getAllAuthor();
    List<Genre> genreList = getAllGenre();

    bookList.add(new Book(1, "Doughter of Capitan", authorList.get(1), genreList.get(2)));
    bookList.add(new Book(2, "Apocalypse", authorList.get(2), genreList.get(0)));
    bookList.add(new Book(3, "Revolution-1", authorList.get(3), genreList.get(1)));
    bookList.add(new Book(4, "Revolution-2", authorList.get(3), genreList.get(1)));
    bookList.add(new Book(5, "It", authorList.get(0), genreList.get(3)));

    return bookList;
  }

  private List<Author> getAllAuthor() {
    List<Author> authorList = new ArrayList<>();

    authorList.add(new Author(1, "Stephen King"));
    authorList.add(new Author(2, "Alexander Pushkin"));
    authorList.add(new Author(3, "Isaak Newton"));
    authorList.add(new Author(4, "Vladimir Lenin"));

    return authorList;
  }

  private List<Genre> getAllGenre() {
    List<Genre> genreList = new ArrayList<>();

    genreList.add(new Genre(1, "Fantastic"));
    genreList.add(new Genre(2, "Political"));
    genreList.add(new Genre(3, "Novel"));
    genreList.add(new Genre(4, "Horror"));
    return genreList;
  }
}