package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Role;
import java.util.List;

public interface RoleService {

  void saveOrUpdate(Role role);

  Role getById(Long id);

  List<Role> getAll();

  void deleteById(Long id);

}
