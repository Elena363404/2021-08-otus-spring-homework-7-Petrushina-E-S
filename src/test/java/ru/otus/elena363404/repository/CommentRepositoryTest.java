package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
  private static final long COMMENT_ID_FOR_UPDATE = 2;
  private static final int EXPECTED_NUMBER_OF_COMMENTS = 3;

  @Autowired
  private TestEntityManager em;

  @Autowired
  private CommentRepository commentRepository;

  @DisplayName("Add comment in the DB")
  @Test
  void shouldInsertComment() {
    Comment expectedComment = new Comment(4,"Norm", em.find(Book.class, 2L));
    commentRepository.save(expectedComment);
    Comment actualComment = em.find(Comment.class, expectedComment.getId());
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
    Book book = em.find(Book.class, EXISTING_BOOK_ID);
    val optionalActualComment = commentRepository.findByBook(book);
    List<Comment> commentList = getCommentListByBookId(EXISTING_BOOK_ID);

    assertThat(commentList).isEqualTo(optionalActualComment);
  }

  @DisplayName("Update comment by ID")
  @Test
  void shouldUpdateExpectedCommentById() {
    Comment newComment = new Comment(COMMENT_ID_FOR_UPDATE, "Comment after update!", em.find(Book.class, 2L));
    commentRepository.save(newComment);
    Comment updatedComment = em.find(Comment.class, COMMENT_ID_FOR_UPDATE);

    assertThat(newComment).isEqualTo(updatedComment);
  }

  @DisplayName("Delete comment by ID")
  @Test
  void shouldCorrectDeleteCommentById() {
    Comment commentBeforeDelete = em.find(Comment.class, COMMENT_ID_FOR_DELETE);
    assertNotNull(commentBeforeDelete);
    commentRepository.deleteById(COMMENT_ID_FOR_DELETE);
    em.flush();
    Comment commentAfterDelete = em.find(Comment.class, COMMENT_ID_FOR_DELETE);
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
    Book book = em.find(Book.class, bookId);
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment(1, "Good book!", book));
    commentList.add(new Comment(2, "Bad book!",book));

    return commentList;
  }
}