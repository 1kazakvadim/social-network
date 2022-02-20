package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.RoleDao;
import by.sam_solutions.kazak.social_network.entities.Role;
import by.sam_solutions.kazak.social_network.services.RoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

  private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

  @Autowired
  private RoleDao roleDao;

  @Override
  @Transactional
  public void saveOrUpdate(Role role) {
    logger.debug("saveOrUpdate({})", role);
    roleDao.saveOrUpdate(role);
  }

  @Override
  @Transactional(readOnly = true)
  public Role getById(Long id) {
    logger.debug("get role by id = {}", id);
    return roleDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Role> getAll() {
    logger.debug("get all roles");
    return roleDao.getAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete role with id = {}", id);
    roleDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Role findByName(String name) {
    logger.debug("get role with name = {}", name);
    return roleDao.findByName(name);
  }

}
