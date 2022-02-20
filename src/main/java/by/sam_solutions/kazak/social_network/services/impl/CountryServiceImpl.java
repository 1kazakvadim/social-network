package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.CountryDao;
import by.sam_solutions.kazak.social_network.entities.Country;
import by.sam_solutions.kazak.social_network.services.CountryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl implements CountryService {

  private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

  @Autowired
  private CountryDao countryDao;

  @Override
  @Transactional
  public void saveOrUpdate(Country country) {
    logger.debug("saveOrUpdate({})", country);
    countryDao.saveOrUpdate(country);
  }

  @Override
  @Transactional(readOnly = true)
  public Country getById(Long id) {
    logger.debug("get country by id = {}", id);
    return countryDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Country> getAll() {
    logger.debug("get all countries");
    return countryDao.getAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete country with id = {}", id);
    countryDao.deleteById(id);
  }

}
