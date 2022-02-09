package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.Relationship;
import javax.annotation.Resource;
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
public class RelationshipServiceTest {

  @Resource
  private RelationshipService relationshipService;

  private Relationship relationship;

  @Before
  public void addValues() {
    relationship = new Relationship();
    relationship.setId(1L);
    relationship.setName("name");
  }

  @After
  public void removeValues() {
    relationship = null;
  }

  @Test
  @Transactional
  public void it_should_save_relationship() {
    assertNotNull(relationshipService);
    relationshipService.saveOrUpdate(relationship);
    Relationship savedRelationship = relationshipService.getById(relationship.getId());
    assertEquals(savedRelationship.getId(), relationship.getId());
    assertEquals(savedRelationship.getName(), relationship.getName());
  }

  @Test
  @Transactional
  public void it_should_update_relationship() {
    assertNotNull(relationshipService);
    relationshipService.saveOrUpdate(relationship);
    Relationship relationshipForUpdate = relationshipService.getById(relationship.getId());
    relationshipForUpdate.setName("updatedName");
    relationshipService.saveOrUpdate(relationshipForUpdate);
    Relationship updatedRelationship = relationshipService.getById(relationship.getId());
    assertEquals(updatedRelationship.getName(),
        relationshipForUpdate.getName());
  }

  @Test
  @Transactional
  public void it_should_get_relationship_by_id() {
    assertNotNull(relationshipService);
    relationshipService.saveOrUpdate(relationship);
    Relationship relationshipById = relationshipService.getById(relationship.getId());
    assertEquals(relationshipById.getId(), relationship.getId());
    assertEquals(relationshipById.getName(), relationship.getName());
  }

}
