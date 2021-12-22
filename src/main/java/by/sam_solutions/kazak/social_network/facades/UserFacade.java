package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import java.util.List;

public interface UserFacade {

  List<UserDTO> getAll();

  UserDTO findByEmail(String email);

  boolean isEmailExists(String email);

  boolean isPasswordMatchConfirmPassword(String password, String confirmPassword);

  void registerUserAndSendVerificationToken(BasicInformation basicInformation, String email,
      String password, String appUrl);

  void confirmRegisterUser(String verificationToken);

}
