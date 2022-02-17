package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.RelationshipDao;
import by.sam_solutions.kazak.social_network.entities.Relationship;
import by.sam_solutions.kazak.social_network.services.RelationshipService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationshipServiceImpl implements RelationshipService {

  private final Logger logger = LoggerFactory.getLogger(RelationshipServiceImpl.class);

  @Autowired
  private RelationshipDao relationshipDao;

  @Override
  @Transactional
  public void saveOrUpdate(Relationship relationship) {
    logger.debug("saveOrUpdate({})", relationship);
    relationshipDao.saveOrUpdate(relationship);
  }

  @Override
  @Transactional(readOnly = true)
  public Relationship getById(Long id) {
    logger.debug("get relationship by id = {}", id);
    return relationshipDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Relationship> getAll() {
    logger.debug("get all relationships");
    return relationshipDao.getAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete relationship with id = {}", id);
    relationshipDao.deleteById(id);
  }

}
