package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Comment;
import java.util.List;

public interface CommentService {

  void saveOrUpdate(Comment comment);

  Comment getById(Long id);

  List<Comment> getAll();

  List<Comment> getAllByPhotoId(Long id);

  void deleteById(Long id);

}
