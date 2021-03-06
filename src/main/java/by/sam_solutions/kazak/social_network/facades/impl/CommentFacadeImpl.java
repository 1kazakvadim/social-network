package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Comment;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.CommentFacade;
import by.sam_solutions.kazak.social_network.services.CommentService;
import by.sam_solutions.kazak.social_network.services.PhotoService;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFacadeImpl implements CommentFacade {

  @Autowired
  private CommentService commentService;

  @Autowired
  private PhotoService photoService;

  @Autowired
  private ProfileService profileService;

  @Override
  public Comment getById(Long id) {
    return commentService.getById(id);
  }

  @Override
  public List<Comment> getAllByPhotoId(Long id) {
    return commentService.getAllByPhotoId(id);
  }

  @Override
  public List<Comment> getAllByPhotoId(Long id, Integer page, Integer size) {
    return commentService.getAllByPhotoId(id, page, size);
  }

  @Override
  public void addComment(String text, Long photoId, Long userId) {
    Comment comment = new Comment();
    comment.setText(text);
    comment.setProfile(profileService.getProfileByUserId(userId));
    comment.setPhoto(photoService.getById(photoId));
    comment.setTimeCreation(LocalDateTime.now());
    commentService.saveOrUpdate(comment);
  }

  @Override
  public void deleteComment(Long commentId, Profile profile) {
    commentService.deleteById(commentId);
  }

  @Override
  public Long countByPhotoId(Long id) {
    return commentService.countByPhotoId(id);
  }

}
