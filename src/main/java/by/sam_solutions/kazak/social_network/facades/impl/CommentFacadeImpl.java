package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Comment;
import by.sam_solutions.kazak.social_network.facades.CommentFacade;
import by.sam_solutions.kazak.social_network.services.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFacadeImpl implements CommentFacade {

  @Autowired
  private CommentService commentService;

  @Override
  public List<Comment> getAllByPhotoId(Long id) {
    return commentService.getAllByPhotoId(id);
  }
}
