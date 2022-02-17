package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.Country;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppContextConfig.class)
@Transactional
public class CountryServiceTest {

  private final Logger logger = LoggerFactory.getLogger(CountryServiceTest.class);

  @Autowired
  private CountryService countryService;

  private Country country;

  @Before
  public void addValues() {
    country = new Country();
    country.setName("country");
    country.setISOCode("iso");
    countryService.saveOrUpdate(country);
  }

  @After
  public void removeValues() {
    countryService.deleteById(country.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");
    country.setName("updatedName");
    country.setISOCode("updatedISOCode");
    countryService.saveOrUpdate(country);
    Country updatedCountry = countryService.getById(country.getId());
    assertEquals(updatedCountry.getId(), country.getId());
    assertEquals(updatedCountry.getName(), country.getName());
    assertEquals(updatedCountry.getISOCode(), country.getISOCode());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Country countryById = countryService.getById(country.getId());
    assertEquals(country.getId(), countryById.getId());
    assertEquals(country.getName(), countryById.getName());
    assertEquals(country.getISOCode(), countryById.getISOCode());
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    List<Country> countries = countryService.getAll();
    assertNotNull(countries);
    assertEquals(1, countries.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    countryService.deleteById(country.getId());
    assertNull(countryService.getById(country.getId()));
  }

}
