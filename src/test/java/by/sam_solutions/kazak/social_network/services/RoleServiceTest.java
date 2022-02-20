package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.Role;
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
public class RoleServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(RoleServiceTest.class);

  private static final String DEFAULT_TEST_ROLE_NAME = "test";

  @Autowired
  private RoleService roleService;

  private Role role;

  @BeforeTransaction
  public void addValues() {
    role = new Role();
    role.setName(DEFAULT_TEST_ROLE_NAME);
    roleService.saveOrUpdate(role);
  }

  @AfterTransaction
  public void removeValues() {
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final String updatedTestName = "updatedTestName";

    Role role = new Role();
    role.setId(this.role.getId());
    role.setName(updatedTestName);
    roleService.saveOrUpdate(role);
    Role updatedRole = roleService.getById(role.getId());
    assertNotNull(updatedRole);
    assertEquals(updatedTestName, updatedRole.getName());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Role roleById = roleService.getById(role.getId());
    assertNotNull(roleById);
    assertEquals(roleById.getId(), role.getId());
    assertEquals(roleById.getName(), DEFAULT_TEST_ROLE_NAME);
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    List<Role> roles = roleService.getAll();
    assertNotNull(roles);
    assertEquals(1, roles.size());
  }

  @Test
  public void testFindByName() {
    logger.debug("Execute test: testFindByName()");
    Role roleByName = roleService.findByName(DEFAULT_TEST_ROLE_NAME);
    assertNotNull(roleByName);
    assertEquals(roleByName.getId(), role.getId());
    assertEquals(roleByName.getName(), DEFAULT_TEST_ROLE_NAME);
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    roleService.deleteById(role.getId());
    assertNull(roleService.getById(role.getId()));
  }

}
