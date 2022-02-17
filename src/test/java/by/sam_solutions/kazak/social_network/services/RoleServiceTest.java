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

  private final Logger logger = LoggerFactory.getLogger(RoleServiceTest.class);

  @Autowired
  private RoleService roleService;

  private Role role;

  @BeforeTransaction
  public void addValues() {
    role = new Role();
    role.setName("role");
    roleService.saveOrUpdate(role);
  }

  @AfterTransaction
  public void removeValues() {
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSave()");
    role.setName("updatedRole");
    roleService.saveOrUpdate(role);
    Role updatedRole = roleService.getById(role.getId());
    assertEquals(updatedRole.getId(), role.getId());
    assertEquals(updatedRole.getName(), role.getName());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Role roleById = roleService.getById(role.getId());
    assertEquals(roleById.getId(), role.getId());
    assertEquals(roleById.getName(), role.getName());
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    roleService.saveOrUpdate(role);
    List<Role> roles = roleService.getAll();
    assertNotNull(roles);
    assertEquals(3, roles.size());
  }

  @Test
  public void testFindByName() {
    logger.debug("Execute test: testFindByName()");
    Role roleByName = roleService.findByName(role.getName());
    assertEquals(roleByName.getId(), role.getId());
    assertEquals(roleByName.getName(), role.getName());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    roleService.deleteById(role.getId());
    assertNull(roleService.getById(role.getId()));
  }

}
