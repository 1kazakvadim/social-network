package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppContextConfig.class)
@Transactional
public class BasicInformationServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(BasicInformationServiceTest.class);

  private static final String DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME = "testFirstname";
  private static final String DEFAULT_TEST_BASIC_INFORMATION_LASTNAME = "testLastname";
  private static final String DEFAULT_TEST_BASIC_INFORMATION_GENDER = "MALE";
  private static final String DEFAULT_TEST_BASIC_INFORMATION_GENDER_INVALID = "INVALID";
  private static final LocalDate DEFAULT_TEST_BASIC_INFORMATION_BIRTHDAY = LocalDate.now()
      .minusYears(1);
  private static final LocalDate DEFAULT_TEST_BASIC_INFORMATION_BIRTHDAY_INVALID = LocalDate.now()
      .plusYears(1);

  @Autowired
  private BasicInformationService basicInformationService;

  private BasicInformation basicInformation;

  @BeforeTransaction
  public void addValues() {
    basicInformation = new BasicInformation();
    basicInformation.setFirstname(DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME);
    basicInformation.setLastname(DEFAULT_TEST_BASIC_INFORMATION_LASTNAME);
    basicInformation.setGender(DEFAULT_TEST_BASIC_INFORMATION_GENDER);
    basicInformation.setBirthday(DEFAULT_TEST_BASIC_INFORMATION_BIRTHDAY);
    basicInformationService.saveOrUpdate(basicInformation);
  }

  @AfterTransaction
  public void removeValues() {
    basicInformationService.deleteById(basicInformation.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final String updatedTestFirstname = "updatedTestFirstname";
    final String updatedTestLastname = "updatedTestLastname";
    final String updatedTestGender = "updatedTestGender";
    final LocalDate updatedTestBirthday = LocalDate.now().minusYears(2);

    BasicInformation basicInformation = new BasicInformation();
    basicInformation.setId(this.basicInformation.getId());
    basicInformation.setFirstname(updatedTestFirstname);
    basicInformation.setLastname(updatedTestLastname);
    basicInformation.setGender(updatedTestGender);
    basicInformation.setBirthday(updatedTestBirthday);
    basicInformationService.saveOrUpdate(basicInformation);
    BasicInformation updatedBasicInformation = basicInformationService.getById(
        basicInformation.getId());
    assertNotNull(basicInformation);
    assertEquals(updatedTestFirstname, updatedBasicInformation.getFirstname());
    assertEquals(updatedTestLastname, updatedBasicInformation.getLastname());
    assertEquals(updatedTestGender, updatedBasicInformation.getGender());
    assertEquals(updatedTestBirthday, updatedBasicInformation.getBirthday());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    BasicInformation basicInformationById = basicInformationService.getById(
        basicInformation.getId());
    assertEquals(basicInformationById.getFirstname(), DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME);
    assertEquals(basicInformationById.getLastname(), DEFAULT_TEST_BASIC_INFORMATION_LASTNAME);
    assertEquals(basicInformationById.getGender(), DEFAULT_TEST_BASIC_INFORMATION_GENDER);
    assertEquals(basicInformationById.getBirthday(), DEFAULT_TEST_BASIC_INFORMATION_BIRTHDAY);
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    basicInformationService.deleteById(basicInformation.getId());
    assertNull(basicInformationService.getById(basicInformation.getId()));
  }

  @Test
  public void testIsGenderValid() {
    logger.debug("Execute test: testIsGenderValid()");
    assertTrue(basicInformationService.isGenderValid(Gender.valueOf(basicInformation.getGender())));
  }

  @Test(expected = IllegalArgumentException.class)
  @Rollback
  public void testIsGenderInvalid() {
    logger.debug("Execute test: testIsGenderInvalid()");
    basicInformation.setGender(DEFAULT_TEST_BASIC_INFORMATION_GENDER_INVALID);
    basicInformationService.saveOrUpdate(basicInformation);
    basicInformationService.isGenderValid(Gender.valueOf(basicInformation.getGender()));
  }

  @Test
  public void testIsBirthdayValid() {
    logger.debug("Execute test: testIsBirthdayValid()");
    assertTrue(
        basicInformationService.isBirthdayDateValid(DEFAULT_TEST_BASIC_INFORMATION_BIRTHDAY));
  }

  @Test
  @Rollback
  public void testIsBirthdayInvalid() {
    logger.debug("Execute test: testIsBirthdayInvalid()");
    basicInformation.setBirthday(DEFAULT_TEST_BASIC_INFORMATION_BIRTHDAY_INVALID);
    basicInformationService.saveOrUpdate(basicInformation);
    assertFalse(basicInformationService.isBirthdayDateValid(basicInformation.getBirthday()));
  }

}
