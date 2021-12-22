package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Role;

public interface RoleDao extends IAbstractBaseDao<Role> {

  Role findByName(String name);

}
