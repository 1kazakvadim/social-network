package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.Country;
import java.util.List;
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
public class CountryServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(CountryServiceTest.class);

  private static final String DEFAULT_TEST_COUNTRY_NAME = "testName";
  private static final String DEFAULT_TEST_ISO_NAME = "testISO";

  @Autowired
  private CountryService countryService;

  private Country country;

  @BeforeTransaction
  public void addValues() {
    country = new Country();
    country.setName(DEFAULT_TEST_COUNTRY_NAME);
    country.setISOCode(DEFAULT_TEST_ISO_NAME);
    countryService.saveOrUpdate(country);
  }

  @AfterTransaction
  public void removeValues() {
    countryService.deleteById(country.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final String updatedTestCountryName = "updatedTestName";
    final String updatedTestISOName = "updatedTestISOName";

    Country country = new Country();
    country.setId(this.country.getId());
    country.setName(updatedTestCountryName);
    country.setISOCode(updatedTestISOName);
    countryService.saveOrUpdate(country);
    Country updatedCountry = countryService.getById(country.getId());
    assertNotNull(updatedCountry);
    assertEquals(updatedTestCountryName, updatedCountry.getName());
    assertEquals(updatedTestISOName, updatedCountry.getISOCode());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Country countryById = countryService.getById(country.getId());
    assertNotNull(countryById);
    assertEquals(countryById.getId(), country.getId());
    assertEquals(countryById.getName(), DEFAULT_TEST_COUNTRY_NAME);
    assertEquals(countryById.getISOCode(), DEFAULT_TEST_ISO_NAME);
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
