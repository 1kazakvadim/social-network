package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.CommentDao;
import by.sam_solutions.kazak.social_network.entities.Comment;
import by.sam_solutions.kazak.social_network.services.CommentService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

  @Autowired
  private CommentDao commentDao;

  @Override
  public void saveOrUpdate(Comment comment) {
    logger.debug("saveOrUpdate({})", comment);
    commentDao.saveOrUpdate(comment);
  }

  @Override
  public Comment getById(Long id) {
    logger.debug("get comment by id = {}", id);
    return commentDao.getById(id);
  }

  @Override
  public List<Comment> getAll() {
    logger.debug("get all comments");
    return commentDao.getAll();
  }

  @Override
  public List<Comment> getAllByPhotoId(Long id) {
    logger.debug("get all comments with photo id = {}", id);
    return commentDao.getAllByPhotoId(id);
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete comment with id = {}", id);
    commentDao.deleteById(id);
  }

}
