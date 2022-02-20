package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.PhotoDao;
import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.services.PhotoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoServiceImpl implements PhotoService {

  private static final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

  @Autowired
  private PhotoDao photoDao;

  @Override
  @Transactional
  public void saveOrUpdate(Photo photo) {
    logger.debug("saveOrUpdate({})", photo);
    photoDao.saveOrUpdate(photo);
  }

  @Override
  @Transactional(readOnly = true)
  public Photo getById(Long id) {
    logger.debug("get photo by id = {}", id);
    return photoDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Photo> getAll() {
    logger.debug("get all photos");
    return photoDao.getAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Photo> getAllByProfileId(Long id, Integer page, Integer maxResultsPerPage) {
    logger.debug("get all photos by profile id = {}", id);
    return photoDao.getAllByProfileId(id, page, maxResultsPerPage);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete photo with id = {}", id);
    photoDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Photo> getAllByProfileId(Long id) {
    logger.debug("get all photos by profile id = {}", id);
    return photoDao.getAllByProfileId(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Long countByProfileId(Long id) {
    logger.debug("count photos by profile id = {}", id);
    return photoDao.countByProfileId(id);
  }

}
