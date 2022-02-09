package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import by.sam_solutions.kazak.social_network.entities.Relationship;
import java.time.LocalDate;
import javax.annotation.Resource;
import org.apache.commons.lang3.EnumUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {TestAppContextConfig.class},
    loader = AnnotationConfigContextLoader.class)
public class BasicInformationServiceTest {

  @Resource
  private BasicInformationService basicInformationService;

  private BasicInformation basicInformation;

  @Before
  public void addValues() {
    basicInformation = new BasicInformation();
    basicInformation.setId(1L);
    basicInformation.setRelationship(new Relationship());
    basicInformation.setGender("MALE");
    basicInformation.setLastname("lastname");
    basicInformation.setFirstname("firstname");
    basicInformation.setBirthday(LocalDate.now().minusDays(1));
  }

  @After
  public void removeValues() {
    basicInformation = null;
  }

  @Test
  @Transactional
  public void it_should_save_basicInformation() {
    assertNotNull(basicInformationService);
    basicInformationService.saveOrUpdate(basicInformation);
    BasicInformation savedBasicInformation = basicInformationService.getById(
        basicInformation.getId());
    assertEquals(savedBasicInformation.getId(), basicInformation.getId());
    assertEquals(savedBasicInformation.getRelationship(), basicInformation.getRelationship());
    assertEquals(savedBasicInformation.getGender(), basicInformation.getGender());
    assertEquals(savedBasicInformation.getLastname(), basicInformation.getLastname());
    assertEquals(savedBasicInformation.getFirstname(), basicInformation.getFirstname());
    assertEquals(savedBasicInformation.getBirthday(), basicInformation.getBirthday());
  }

  @Test
  @Transactional
  public void it_should_update_basicInformation() {
    assertNotNull(basicInformationService);
    basicInformationService.saveOrUpdate(basicInformation);
    BasicInformation basicInformationForUpdate = basicInformationService.getById(
        basicInformation.getId());
    basicInformation.setGender("updatedGender");
    basicInformation.setLastname("updatedLastname");
    basicInformation.setFirstname("updatedFirstname");
    basicInformation.setBirthday(LocalDate.of(2021, 1, 1));
    basicInformationService.saveOrUpdate(basicInformationForUpdate);
    BasicInformation updatedBasicInformation = basicInformationService.getById(
        basicInformation.getId());
    assertEquals(updatedBasicInformation.getGender(), basicInformationForUpdate.getGender());
    assertEquals(updatedBasicInformation.getLastname(), basicInformationForUpdate.getLastname());
    assertEquals(updatedBasicInformation.getFirstname(), basicInformationForUpdate.getFirstname());
    assertEquals(updatedBasicInformation.getBirthday(), basicInformationForUpdate.getBirthday());
  }

  @Test
  @Transactional
  public void it_should_get_basicInformation_by_id() {
    assertNotNull(basicInformationService);
    basicInformationService.saveOrUpdate(basicInformation);
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
  public void is_gender_valid() {
    assertTrue(EnumUtils.isValidEnum(Gender.class, basicInformation.getGender()));
  }

  @Test
  public void is_gender_invalid() {
    basicInformation.setGender("INVALID_GENDER");
    assertFalse(EnumUtils.isValidEnum(Gender.class, basicInformation.getGender()));
  }

  @Test
  public void is_birthday_valid() {
    assertTrue(basicInformation.getBirthday().isBefore(LocalDate.now()));
  }

  @Test
  public void is_birthday_invalid() {
    basicInformation.setBirthday(LocalDate.now().plusDays(1));
    assertFalse(basicInformation.getBirthday().isBefore(LocalDate.now()));
  }

}
