package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Role;
import java.util.List;

public interface RoleService {

  void save(Role role);

  void update(Role role);

  Role getById(Integer id);

  List<Role> getAll();

  void deleteById(Integer id);

}
