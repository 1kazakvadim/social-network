package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.BasicInformationDao;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import by.sam_solutions.kazak.social_network.services.BasicInformationService;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasicInformationServiceImpl implements BasicInformationService {

  private final Logger logger = LoggerFactory.getLogger(BasicInformationServiceImpl.class);

  @Autowired
  private BasicInformationDao basicInformationDao;

  @Override
  @Transactional
  public void saveOrUpdate(BasicInformation basicInformation) {
    logger.debug("saveOrUpdate({})", basicInformation);
    basicInformationDao.saveOrUpdate(basicInformation);
  }

  @Override
  @Transactional(readOnly = true)
  public BasicInformation getById(Long id) {
    logger.debug("get basic information by id = {}", id);
    return basicInformationDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BasicInformation> getAll() {
    logger.debug("get all basic information");
    return basicInformationDao.getAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete basic information with id = {}", id);
    basicInformationDao.deleteById(id);
  }

  @Override
  public boolean isGenderValid(Gender gender) {
    logger.debug("is gender valid = {}", gender.getName());
    return EnumUtils.isValidEnum(Gender.class, gender.getName());
  }

  @Override
  public boolean isBirthdayDateValid(LocalDate birthday) {
    logger.debug("is birthday date valid = {}", birthday);
    return birthday.isBefore(LocalDate.now());
  }

}
