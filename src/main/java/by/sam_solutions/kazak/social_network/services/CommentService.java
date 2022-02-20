package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Comment;
import java.util.List;

public interface CommentService {

  void saveOrUpdate(Comment comment);

  Comment getById(Long id);

  List<Comment> getAllByPhotoId(Long id);

  List<Comment> getAllByPhotoId(Long id, Integer page, Integer size);

  void deleteById(Long id);

  Long countByPhotoId(Long id);

}
