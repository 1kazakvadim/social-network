package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.UserConverter;
import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.services.UserService;
import by.sam_solutions.kazak.social_network.services.VerificationTokenService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserFacadeImpl implements UserFacade {

  @Autowired
  private UserService userService;

  @Autowired
  private VerificationTokenService verificationTokenService;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private JavaMailSender mailSender;


  @Override
  public List<UserDTO> getAll() {
    List<UserDTO> usersDTO = new ArrayList<>();
    List<User> users = userService.getAll();
    for (User user : users) {
      try {
        usersDTO.add((UserDTO) userConverter.convertSourceToTargetClass(user, UserDTO.class));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return usersDTO;
  }

  @Override
  public UserDTO findByEmail(String email) {
    User user = userService.findByEmail(email);
    UserDTO userDTO = new UserDTO();
    try {
      userDTO = (UserDTO) userConverter.convertSourceToTargetClass(user, UserDTO.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userDTO;
  }

  public boolean isEmailExists(String email) {
    return userService.isEmailExists(email);
  }

  @Override
  public boolean isPasswordMatchConfirmPassword(String password, String confirmPassword) {
    return userService.isPasswordMatchConfirmPassword(password, confirmPassword);
  }

  @Override
  @Transactional
  public void registerUserAndSendVerificationToken(BasicInformation basicInformation, String email,
      String password, String appUrl) {
    User user = userService.registerUser(basicInformation, email, password);
    VerificationToken verificationToken = verificationTokenService.createVerificationToken(user);
    mailSender.send(
        verificationTokenService.constructVerificationTokenEmail(appUrl, verificationToken, user));
  }

  @Override
  public void confirmRegisterUser(String token) {
    userService.confirmRegisterUser(token);
  }

}
