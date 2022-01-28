package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Comment;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface CommentFacade {

  List<Comment> getAllByPhotoId(Long id);

  void addComment(String text, Long photoId, Long userId);

  boolean deleteComment(Long commentId, Profile profile);

}
