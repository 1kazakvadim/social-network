package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.BasicInformationDao;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BasicInformationDaoImpl extends AbstractBaseDao<BasicInformation> implements
    BasicInformationDao {

  @Override
  public void saveOrUpdate(BasicInformation obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public BasicInformation getById(Long id) {
    return getById(BasicInformation.class, id);
  }

  @Override
  public List<BasicInformation> getAll() {
    return getAll(BasicInformation.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(BasicInformation.class, id));
  }

}
