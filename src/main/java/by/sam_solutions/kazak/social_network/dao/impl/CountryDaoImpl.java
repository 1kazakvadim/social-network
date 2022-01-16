package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.CountryDao;
import by.sam_solutions.kazak.social_network.entities.Country;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CountryDaoImpl extends AbstractBaseDao<Country> implements CountryDao {

  @Override
  public void saveOrUpdate(Country obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Country getById(Long id) {
    return getById(Country.class, id);
  }

  @Override
  public List<Country> getAll() {
    return getAll(Country.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Country.class, id));
  }

}
