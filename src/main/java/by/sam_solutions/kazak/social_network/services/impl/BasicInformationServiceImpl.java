package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.BasicInformationDao;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.services.BasicInformationService;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicInformationServiceImpl implements BasicInformationService {

  private final Logger logger = LoggerFactory.getLogger(BasicInformationServiceImpl.class);

  @Autowired
  private BasicInformationDao basicInformationDao;

  @Override
  public void saveOrUpdate(BasicInformation basicInformation) {
    logger.debug("saveOrUpdate({})", basicInformation);
    basicInformationDao.saveOrUpdate(basicInformation);
  }

  @Override
  public BasicInformation getById(Long id) {
    logger.debug("get basic information by id = {}", id);
    return basicInformationDao.getById(id);
  }

  @Override
  public List<BasicInformation> getAll() {
    logger.debug("get all basic information");
    return basicInformationDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete basic information with id = {}", id);
    basicInformationDao.deleteById(id);
  }

  @Override
  public boolean isGenderValid(String gender) {
    return gender.equals("MALE") || gender.equals("FEMALE");
  }

  @Override
  public boolean isBirthdayDateValid(LocalDate birthday) {
    return !birthday.isAfter(LocalDate.now());
  }

}
