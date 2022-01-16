package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;

public interface UserService {

  void saveOrUpdate(User user);

  User getById(Long id);

  List<User> getAll();

  User getByToken(String token);

  void deleteById(Long id);

  User findByEmail(String email);

  boolean isEmailExists(String email);

  boolean isEmailValid(String email);

  boolean isPasswordValid(String password);

  boolean isUserPassword(Long id, String password);

  boolean isPasswordMatchConfirmPassword(String password, String confirmPassword);

  void changePassword(User user, String password);

  void changeEmail(User user, String email);

  void disableUser(Long id);

  User registerUser(Profile profile);

  void confirmRegisterUser(String token);

}
