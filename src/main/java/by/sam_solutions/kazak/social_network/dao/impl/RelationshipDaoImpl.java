package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.RelationshipDao;
import by.sam_solutions.kazak.social_network.entities.Relationship;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RelationshipDaoImpl extends AbstractBaseDao<Relationship> implements RelationshipDao {

  @Override
  public void saveOrUpdate(Relationship obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Relationship getById(Long id) {
    return getById(Relationship.class, id);
  }

  @Override
  public List<Relationship> getAll() {
    return getAll(Relationship.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Relationship.class, id));
  }

}
