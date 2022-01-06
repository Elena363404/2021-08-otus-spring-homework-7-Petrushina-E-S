package ru.otus.elena363404.service;

public interface CommentService {

  void createComment();

  void updateComment();

  void deleteComment();

  void getCommentById();

  void getCommentByBookId();

  void getAllComment();
}
