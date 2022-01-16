package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.ProfileConverter;
import by.sam_solutions.kazak.social_network.converters.UserConverter;
import by.sam_solutions.kazak.social_network.dto.ProfileDTO;
import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.Token;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.services.TokenService;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserFacadeImpl implements UserFacade {

  private final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

  @Autowired
  private UserService userService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private ProfileConverter profileConverter;

  @Autowired
  private JavaMailSender mailSender;


  @Override
  public List<UserDTO> getAll() {
    List<UserDTO> usersDTO = new ArrayList<>();
    List<User> users = userService.getAll();
    for (User user : users) {
      try {
        usersDTO.add((UserDTO) userConverter.convertSourceToTargetClass(user, UserDTO.class));
      } catch (Exception exp) {
        logger.debug("Error converting {} to {}", User.class.getName(),
            UserDTO.class.getName());
        exp.printStackTrace();
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
      logger.debug("Error converting {} to {}", User.class.getName(),
          UserDTO.class.getName());
      e.printStackTrace();
    }
    return userDTO;
  }

  @Override
  public boolean isEmailExists(String email) {
    return userService.isEmailExists(email);
  }

  @Override
  public boolean isEmailValid(String email) {
    return userService.isEmailValid(email);
  }

  @Override
  public boolean isPasswordMatchConfirmPassword(String password, String confirmPassword) {
    return userService.isPasswordMatchConfirmPassword(password, confirmPassword);
  }

  @Override
  public boolean isPasswordValid(String password) {
    return userService.isPasswordValid(password);
  }

  @Override
  public boolean isUserPassword(Long id, String password) {
    return userService.isUserPassword(id, password);
  }

  @Override
  public void changePassword(Long id, String password) {
    userService.changePassword(userService.getById(id), password);
  }

  @Override
  public void changeEmail(Long id, String email) {
    userService.changeEmail(userService.getById(id), email);
  }

  @Override
  public void disableUser(UserPrincipal user) {
    userService.disableUser(user.getId());
  }

  @Override
  @Transactional
  public void registerUserAndSendVerificationToken(ProfileDTO profileDTO, String appUrl,
      Locale locale) {
    Profile profile = new Profile();
    try {
      profile = (Profile) profileConverter.convertTargetToSourceClass(profileDTO, Profile.class);
    } catch (Exception e) {
      logger.debug("Error converting {} to {}", ProfileDTO.class.getName(),
          Profile.class.getName());
      e.printStackTrace();
    }
    User user = userService.registerUser(profile);
    Token token = tokenService.createVerificationToken(user);
    mailSender.send(
        tokenService.constructVerificationTokenEmail(appUrl, token, user, locale));
  }

  @Override
  public void confirmRegisterUser(String token) {
    userService.confirmRegisterUser(token);
  }

}
