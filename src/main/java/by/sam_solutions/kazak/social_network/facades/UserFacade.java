package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.dto.ProfileDTO;
import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import java.util.List;
import java.util.Locale;

public interface UserFacade {

  List<UserDTO> getAll();

  UserDTO findByEmail(String email);

  boolean isEmailExists(String email);

  boolean isEmailValid(String email);

  boolean isPasswordMatchConfirmPassword(String password, String confirmPassword);

  boolean isPasswordValid(String password);

  boolean isUserPassword(Long id, String password);

  void changePassword(Long id, String password);

  void changeEmail(Long id, String email);

  void disableUser(UserPrincipal user);

  void registerUserAndSendVerificationToken(ProfileDTO profileDTO, String appUrl, Locale locale);

  void confirmRegisterUser(String token);

}
