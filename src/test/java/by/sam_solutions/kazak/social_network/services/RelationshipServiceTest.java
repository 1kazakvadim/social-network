package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.Relationship;
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
public class RelationshipServiceTest {

  private final Logger logger = LoggerFactory.getLogger(RelationshipServiceTest.class);

  @Autowired
  private RelationshipService relationshipService;

  private Relationship relationship;

  @BeforeTransaction
  public void addValues() {
    relationship = new Relationship();
    relationship.setName("name");
    relationshipService.saveOrUpdate(relationship);
  }

  @AfterTransaction
  public void removeValues() {
    relationshipService.deleteById(relationship.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSave()");
    relationship.setName("updatedName");
    relationshipService.saveOrUpdate(relationship);
    Relationship updatedRelationship = relationshipService.getById(relationship.getId());
    assertEquals(updatedRelationship.getId(), relationship.getId());
    assertEquals(updatedRelationship.getName(), relationship.getName());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    relationshipService.saveOrUpdate(relationship);
    Relationship relationshipById = relationshipService.getById(relationship.getId());
    assertEquals(relationshipById.getId(), relationship.getId());
    assertEquals(relationshipById.getName(), relationship.getName());
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    List<Relationship> relationships = relationshipService.getAll();
    assertNotNull(relationships);
    assertEquals(1, relationships.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    relationshipService.deleteById(relationship.getId());
    assertNull(relationshipService.getById(relationship.getId()));
  }

}
