package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.RoleDao;
import by.sam_solutions.kazak.social_network.entities.Role;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl extends AbstractBaseDao<Role> implements RoleDao {

  @Override
  public void saveOrUpdate(Role obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Role getById(Long id) {
    return getById(Role.class, id);
  }

  @Override
  public List<Role> getAll() {
    return getAll(Role.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Role.class, id));
  }

}
