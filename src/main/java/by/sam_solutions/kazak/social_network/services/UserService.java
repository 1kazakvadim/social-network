package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;

public interface UserService {

  void saveOrUpdate(User user);

  User getById(Long id);

  List<User> getAll();

  void deleteById(Long id);

}
