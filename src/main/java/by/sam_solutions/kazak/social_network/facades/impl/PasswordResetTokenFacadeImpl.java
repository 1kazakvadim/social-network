package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.PasswordResetToken;
import by.sam_solutions.kazak.social_network.facades.PasswordResetTokenFacade;
import by.sam_solutions.kazak.social_network.services.PasswordResetTokenService;
import by.sam_solutions.kazak.social_network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetTokenFacadeImpl implements PasswordResetTokenFacade {

  @Autowired
  private PasswordResetTokenService passwordResetTokenService;

  @Autowired
  private UserService userService;

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public PasswordResetToken getByToken(String token) {
    return passwordResetTokenService.getByToken(token);
  }

  @Override
  public void createAndSendPasswordResetToken(String email, String appUrl) {
    PasswordResetToken token = passwordResetTokenService
        .createPasswordResetTokenForUser(email);
    mailSender.send(
        passwordResetTokenService.constructPasswordResetTokenEmail(appUrl, token, token.getUser()));
  }

  @Override
  public void resetUserPassword(String token, String password) {
    userService.changePassword(passwordResetTokenService.getUserByToken(token), password);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return passwordResetTokenService.isTokenExpired(token);
  }

}
