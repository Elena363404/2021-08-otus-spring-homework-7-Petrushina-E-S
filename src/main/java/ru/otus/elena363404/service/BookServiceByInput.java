package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.repository.AuthorRepository;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.GenreRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceByInput implements BookService {

  private final IOService ioService;
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final GenreRepository genreRepository;

  @Override
  public void createBook() {

    ioService.out("Input name for the book: \n");
    String nameBook = ioService.readString();
    ioService.out("Input id Author for the book from List: \n" + authorRepository.findAll());
    long authorId = ioService.getInputId();
    Author author = authorRepository.findById(authorId).stream().findFirst().orElse(null);
    ioService.out("Input id Genre for the book from List: \n" + genreRepository.findAll());
    long genreId = ioService.getInputId();
    Genre genre = genreRepository.findById(genreId).stream().findFirst().orElse(null);
    Book book = new Book(0, nameBook, author, genre);

    bookRepository.save(book);
  }

  @Override
  public void updateBook() {
    ioService.out("Input id of the book for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the book: \n");
    String name = ioService.readString();
    ioService.out("Input id Author for the book from List: \n" + authorRepository.findAll());
    long authorId = ioService.getInputId();
    Author author = authorRepository.findById(authorId).stream().findFirst().orElse(null);
    ioService.out("Input id Genre for the book from List: \n" + genreRepository.findAll());
    long genreId = ioService.getInputId();
    Genre genre = genreRepository.findById(genreId).stream().findFirst().orElse(null);
    Book book = new Book(id, name, author, genre);
    bookRepository.save(book);
  }

  @Override
  public void deleteBook() {
    ioService.out("Input id of the book for delete: \n");
    long id = ioService.getInputId();
    bookRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public void getBookById() {
    ioService.out("Input id of the book: \n");
    long id = ioService.getInputId();
    ioService.out(bookRepository.findById(id)
      .map(a -> "\n" + a)
      .orElse("Book with input ID not found!")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public void getAllBook() {
    ioService.out("All books: \n" + bookRepository.findAll().stream().map(Book::toString).collect(Collectors.joining("\n")));
  }
}
