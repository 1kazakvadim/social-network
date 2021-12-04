package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.RoleDao;
import by.sam_solutions.kazak.social_network.entities.Role;
import by.sam_solutions.kazak.social_network.services.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  private final RoleDao roleDao;

  public RoleServiceImpl(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  @Override
  public void saveOrUpdate(Role role) {
    roleDao.saveOrUpdate(role);
  }

  @Override
  public Role getById(Long id) {
    return roleDao.getById(id);
  }

  @Override
  public List<Role> getAll() {
    return roleDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    roleDao.deleteById(id);
  }

}
