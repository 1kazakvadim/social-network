package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;

public interface UserService {

  void save(User user);

  void update(User user);

  User getById(Integer id);

  List<User> getAll();

  void deleteById(Integer id);

}
