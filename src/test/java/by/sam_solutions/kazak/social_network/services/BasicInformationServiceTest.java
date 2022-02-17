package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import by.sam_solutions.kazak.social_network.entities.Relationship;
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

  private final Logger logger = LoggerFactory.getLogger(BasicInformationServiceTest.class);

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private RelationshipService relationshipService;

  private BasicInformation basicInformation;
  private Relationship relationship;

  @BeforeTransaction
  public void addValues() {
    relationship = new Relationship();
    basicInformation = new BasicInformation();
    relationship.setName("name");
    relationshipService.saveOrUpdate(relationship);
    basicInformation.setRelationship(relationship);
    basicInformation.setGender("MALE");
    basicInformation.setLastname("lastname");
    basicInformation.setFirstname("firstname");
    basicInformation.setBirthday(LocalDate.now().minusDays(1));
    basicInformationService.saveOrUpdate(basicInformation);
  }

  @AfterTransaction
  public void removeValues() {
    basicInformationService.deleteById(basicInformation.getId());
    relationshipService.deleteById(relationship.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");
    basicInformation.setGender("FEMALE");
    basicInformation.setLastname("updatedLastname");
    basicInformation.setFirstname("updatedFirstname");
    basicInformation.setBirthday(LocalDate.now().minusDays(10));
    basicInformationService.saveOrUpdate(basicInformation);
    BasicInformation updatedBasicInformation = basicInformationService.getById(
        basicInformation.getId());
    assertEquals(updatedBasicInformation.getId(), basicInformation.getId());
    assertEquals(updatedBasicInformation.getRelationship(), basicInformation.getRelationship());
    assertEquals(updatedBasicInformation.getGender(), basicInformation.getGender());
    assertEquals(updatedBasicInformation.getLastname(), basicInformation.getLastname());
    assertEquals(updatedBasicInformation.getFirstname(), basicInformation.getFirstname());
    assertEquals(updatedBasicInformation.getBirthday(), basicInformation.getBirthday());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    BasicInformation basicInformationById = basicInformationService.getById(
        basicInformation.getId());
    assertEquals(basicInformationById.getId(), basicInformationById.getId());
    assertEquals(basicInformationById.getRelationship(), basicInformationById.getRelationship());
    assertEquals(basicInformationById.getGender(), basicInformationById.getGender());
    assertEquals(basicInformationById.getLastname(), basicInformationById.getLastname());
    assertEquals(basicInformationById.getFirstname(), basicInformationById.getFirstname());
    assertEquals(basicInformationById.getBirthday(), basicInformationById.getBirthday());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    assertNotNull(basicInformation);
    basicInformationService.deleteById(basicInformation.getId());
    assertNull(basicInformationService.getById(basicInformation.getId()));
  }

  @Test
  public void testIsGenderValid() {
    logger.debug("Execute test: testIsGenderValid()");
    basicInformationService.saveOrUpdate(basicInformation);
    assertTrue(basicInformationService.isGenderValid(Gender.valueOf(basicInformation.getGender())));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsGenderInvalid() {
    logger.debug("Execute test: testIsGenderInvalid()");
    basicInformation.setGender("INVALID_GENDER");
    basicInformationService.saveOrUpdate(basicInformation);
    basicInformationService.isGenderValid(Gender.valueOf(basicInformation.getGender()));
  }

  @Test
  public void testIsBirthdayValid() {
    logger.debug("Execute test: testIsBirthdayValid()");
    basicInformationService.saveOrUpdate(basicInformation);
    assertTrue(basicInformationService.isBirthdayDateValid(basicInformation.getBirthday()));
  }

  @Test
  public void testIsBirthdayInvalid() {
    logger.debug("Execute test: testIsBirthdayInvalid()");
    basicInformation.setBirthday(LocalDate.now().plusDays(1));
    basicInformationService.saveOrUpdate(basicInformation);
    assertFalse(basicInformationService.isBirthdayDateValid(basicInformation.getBirthday()));
  }

}
