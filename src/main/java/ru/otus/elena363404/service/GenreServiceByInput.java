package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.repository.GenreRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceByInput implements GenreService {

  private final IOService ioService;
  private final GenreRepository genreRepository;

  @Override
  public void createGenre() {
    ioService.out("Input name for the genre: \n");
    String nameGenre = ioService.readString();
    Genre genre = new Genre(0, nameGenre);
    genreRepository.save(genre);
  }

  @Override
  public void updateGenre() {
    ioService.out("Input id of the genre for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the genre: \n");
    String name = ioService.readString();
    Genre genre = new Genre(id, name);
    genreRepository.save(genre);
  }

  @Override
  public void deleteGenre() {
    ioService.out("Input id of the genre for delete: \n");
    long id = ioService.getInputId();
    genreRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public void getGenreById() {
    ioService.out("Input id of the genre: \n");
    long id = ioService.getInputId();
    ioService.out(genreRepository.findById(id)
      .map(a -> "\n" + a)
      .orElse("Genre with input ID not found!")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public void getAllGenre() {
    ioService.out("All genres: \n" + genreRepository.findAll().stream().map(Genre::toString).collect(Collectors.joining("\n")));
  }
}
