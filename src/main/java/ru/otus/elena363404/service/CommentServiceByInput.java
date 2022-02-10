package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceByInput implements CommentService {

  private final IOService ioService;
  private final CommentRepository commentRepository;
  private final BookRepository bookRepository;


  @Override
  public void createComment() {
    ioService.out("Input comment: \n");
    String txtComment = ioService.readString();

    ioService.out("Input a book ID for comment: \n" + bookRepository.findAll());
    long idBook = ioService.getInputId();
    Book book = bookRepository.findById(idBook).stream().findFirst().orElse(null);

    Comment comment = new Comment(0, txtComment, book);
    commentRepository.save(comment);
  }

  @Override
  public void updateComment() {
    ioService.out("Input id of the comment for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new comment: \n");
    String txtComment = ioService.readString();

    ioService.out("Input a book ID for comment: \n" + bookRepository.findAll());
    long idBook = ioService.getInputId();
    Book book = bookRepository.findById(idBook).stream().findFirst().orElse(null);

    Comment comment = new Comment(id, txtComment, book);
    commentRepository.save(comment);
  }

  @Override
  public void deleteComment() {
    ioService.out("Input id of the comment for delete: \n");
    long id = ioService.getInputId();
    commentRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public void getCommentById() {
    ioService.out("Input id of the comment: \n");
    long id = ioService.getInputId();
    ioService.out(commentRepository.findById(id)
      .map(a -> "\n" + a)
      .orElse("Comment with input ID not found!")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public void getCommentByBookId() {
    ioService.out("Input book id for getting list of comments: \n");
    long id = ioService.getInputId();
    Book book = bookRepository.findById(id).stream().findFirst().orElse(null);
    List<Comment> commentList = commentRepository.findByBook(book);
    for (int i = 0; i < commentList.size(); i++) {
      ioService.out(commentList.get(i) + "\n");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void getAllComment() {
    ioService.out("All comments: \n" + commentRepository.findAll().stream().map(Comment::toString).collect(Collectors.joining("\n")));
  }
}
