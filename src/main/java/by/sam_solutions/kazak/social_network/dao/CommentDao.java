package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Comment;
import java.util.List;

public interface CommentDao extends IAbstractBaseDao<Comment> {

  List<Comment> getAllByPhotoId(Long id);

  List<Comment> getAllByPhotoId(Long id, Integer page, Integer size);

  Long countByPhotoId(Long id);

}
