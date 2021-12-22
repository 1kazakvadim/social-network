package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.User;

public interface UserDao extends IAbstractBaseDao<User> {

  User findByEmail(String email);

  boolean isEmailExists(String email);

}
