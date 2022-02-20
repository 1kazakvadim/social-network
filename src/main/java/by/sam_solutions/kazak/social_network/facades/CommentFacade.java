package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Comment;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface CommentFacade {

  Comment getById(Long id);

  List<Comment> getAllByPhotoId(Long id);

  List<Comment> getAllByPhotoId(Long id, Integer page, Integer size);

  void addComment(String text, Long photoId, Long userId);

  void deleteComment(Long commentId, Profile profile);

  Long countByPhotoId(Long id);

}
