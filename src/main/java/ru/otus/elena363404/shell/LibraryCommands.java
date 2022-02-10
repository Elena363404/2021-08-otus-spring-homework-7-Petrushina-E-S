package ru.otus.elena363404.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.elena363404.service.AuthorService;
import ru.otus.elena363404.service.BookService;
import ru.otus.elena363404.service.CommentService;
import ru.otus.elena363404.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final CommentService commentService;

  @ShellMethod(value = "all-book", key = {"all books", "all book", "books"})
  public void getAllBook() {
    bookService.getAllBook();
  }

  @ShellMethod(value = "get-book", key = {"get book"})
  public void getBookById() {
    bookService.getBookById();
  }

  @ShellMethod(value = "add-book", key = {"add book", "insert book"})
  public String createBook() {
    bookService.createBook();
    return "You have successfully added the book";
  }

  @ShellMethod(value = "update-book", key = {"update book", "upd book"})
  public String updateBook() {
    bookService.updateBook();
    return "You have successfully updated the book";
  }

  @ShellMethod(value = "delete-book", key = {"delete book", "del book"})
  public String deleteBook() {
    bookService.deleteBook();
    return "You have successfully deleted the book";
  }

  @ShellMethod(value = "all-author", key = {"all author", "authors"})
  public void getAllAuthor() {
    authorService.getAllAuthor();
  }

  @ShellMethod(value = "get-author", key = {"get author"})
  public void getAuthorById() {
    authorService.getAuthorById();
  }

  @ShellMethod(value = "add-author", key = {"add author", "insert author"})
  public String createAuthor() {
    authorService.createAuthor();
    return "You have successfully added the author";
  }

  @ShellMethod(value = "update-author", key = {"update author", "upd author"})
  public String updateAuthor() {
    authorService.updateAuthor();
    return "You have successfully updated the author";
  }

  @ShellMethod(value = "delete-author", key = {"delete author", "del author"})
  public String deleteAuthor() {
    authorService.deleteAuthor();
    return "You have successfully deleted the author";
  }

  @ShellMethod(value = "all-comment", key = {"all comment", "comments"})
  public void getAllComment() {
    commentService.getAllComment();
  }

  @ShellMethod(value = "get-comment", key = {"get comment"})
  public void getCommentById() {
    commentService.getCommentById();
  }

  @ShellMethod(value = "get-comment-by-book", key = {"get comment by book"})
  public void getCommentByBookId() {
    commentService.getCommentByBookId();
  }

  @ShellMethod(value = "add-comment", key = {"add comment", "insert comment"})
  public String createComment() {
    commentService.createComment();
    return "You have successfully added the comment";
  }

  @ShellMethod(value = "update-comment", key = {"update comment", "upd comment"})
  public String updateComment() {
    commentService.updateComment();
    return "You have successfully updated the comment";
  }

  @ShellMethod(value = "delete-comment", key = {"delete comment", "del comment"})
  public String deleteComment() {
    commentService.deleteComment();
    return "You have successfully deleted the comment";
  }

  @ShellMethod(value = "all-genre", key = {"all genre", "genres"})
  public void getAllGenre() {
    genreService.getAllGenre();
  }

  @ShellMethod(value = "get-genre", key = {"get genre"})
  public void getGenreById() {
    genreService.getGenreById();
  }

  @ShellMethod(value = "add-genre", key = {"add genre", "insert genre"})
  public String createGenre() {
    genreService.createGenre();
    return "You have successfully added the genre";
  }

  @ShellMethod(value = "update-genre", key = {"update genre", "upd genre"})
  public String updateGenre() {
    genreService.updateGenre();
    return "You have successfully updated the genre";
  }

  @ShellMethod(value = "delete-genre", key = {"delete genre", "del genre"})
  public String deleteGenre() {
    genreService.deleteGenre();
    return "You have successfully deleted the genre";
  }

}
