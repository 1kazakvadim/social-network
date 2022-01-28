package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.User;

public interface UserDao extends IAbstractBaseDao<User> {

  User getByEmail(String email);

  User getByToken(String token);

  boolean isEmailExists(String email);

}
