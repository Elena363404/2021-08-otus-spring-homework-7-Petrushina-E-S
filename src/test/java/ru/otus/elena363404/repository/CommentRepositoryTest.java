package ru.otus.elena363404.repository;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

  private static final long EXISTING_COMMENT_ID = 2L;
  private static final long EXISTING_BOOK_ID = 1;
  private static final long COMMENT_ID_FOR_DELETE = 3;
  private static final int COMMENT_ID_FOR_UPDATE = 2;
  private static final int EXPECTED_NUMBER_OF_COMMENTS = 3;
  private static final int EXPECTED_QUERIES_COUNT = 23;

  @Autowired
  private TestEntityManager em;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private BookRepository bookRepository;

  @DisplayName("Add comment in the DB")
  @Test
  void shouldInsertComment() {
    Comment expectedComment = new Comment(4,"Norm", bookRepository.findById(2).stream().findFirst().orElse(null));
    commentRepository.save(expectedComment);
    Comment actualComment = commentRepository.findById(expectedComment.getId()).stream().findFirst().orElse(null);
    assertThat(actualComment).isEqualTo(expectedComment);
  }

  @DisplayName("Return comment by ID")
  @Test
  void shouldReturnExpectedCommentById() {
    val optionalActualComment = commentRepository.findById(EXISTING_COMMENT_ID);
    val expextedComment = em.find(Comment.class, EXISTING_COMMENT_ID);
    assertThat(optionalActualComment).isPresent().get().
      usingRecursiveComparison().isEqualTo(expextedComment);
  }

  @DisplayName("Return comment by book ID")
  @Test
  void shouldReturnExpectedCommentByBookId() {
    Book book = bookRepository.findById(EXISTING_BOOK_ID).stream().findFirst().orElse(null);
    val optionalActualComment = commentRepository.findByBook(book);
    List<Comment> commentList = getCommentListByBookId(EXISTING_BOOK_ID);

    assertThat(commentList).isEqualTo(optionalActualComment);
  }

  @DisplayName("Update comment by ID")
  @Test
  void shouldUpdateExpectedCommentById() {
    Comment newComment = new Comment(COMMENT_ID_FOR_UPDATE, "Comment after update!", bookRepository.findById(2).stream().findFirst().orElse(null));
    commentRepository.save(newComment);
    Comment updatedComment = commentRepository.findById(COMMENT_ID_FOR_UPDATE).stream().findFirst().orElse(null);

    assertThat(newComment).isEqualTo(updatedComment);
  }

  @DisplayName("Delete comment by ID")
  @Test
  void shouldCorrectDeleteCommentById() {
    Comment commentBeforeDelete = commentRepository.findById(COMMENT_ID_FOR_DELETE).stream().findFirst().orElse(null);
    assertNotNull(commentBeforeDelete);
    commentRepository.deleteById(COMMENT_ID_FOR_DELETE);
    em.remove(commentBeforeDelete);
    Comment commentAfterDelete = commentRepository.findById(COMMENT_ID_FOR_DELETE).stream().findFirst().orElse(null);
    assertNull(commentAfterDelete);
  }

  @DisplayName("Return list of the comments")
  @Test
  void shouldReturnExpectedCommentsList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val comments = commentRepository.findAll();
    assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
      .allMatch(s -> !s.getComment().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }

  private List<Comment> getCommentListByBookId(long bookId) {
    Book book = bookRepository.findById(bookId).stream().findFirst().orElse(null);
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment(1, "Good book!", book));
    commentList.add(new Comment(2, "Bad book!",book));

    return commentList;
  }
}