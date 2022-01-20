package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Comment;
import java.util.List;

public interface CommentFacade {

  List<Comment> getAllByPhotoId(Long id);

}
