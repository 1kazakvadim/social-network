package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;

public interface UserService {

  void saveOrUpdate(User user);

  User getById(Long id);

  List<User> getAll();

  void deleteById(Long id);

  User findByEmail(String email);

  boolean isEmailExists(String email);

  boolean isPasswordMatchConfirmPassword(String password, String confirmPassword);

  void changePassword(User user, String password);

  User registerUser(BasicInformation basicInformation, String email, String password);

  void confirmRegisterUser(String token);

}
