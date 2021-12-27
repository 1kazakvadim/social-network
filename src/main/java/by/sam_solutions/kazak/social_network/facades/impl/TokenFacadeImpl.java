package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Token;
import by.sam_solutions.kazak.social_network.facades.TokenFacade;
import by.sam_solutions.kazak.social_network.services.TokenService;
import by.sam_solutions.kazak.social_network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class TokenFacadeImpl implements TokenFacade {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private UserService userService;

  @Override
  public Token getByToken(String token) {
    return tokenService.getByToken(token);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return tokenService.isTokenExpired(token);
  }

  @Override
  public void createAndSendPasswordResetToken(String email, String appUrl) {
    Token token = tokenService
        .createPasswordResetToken(email);
    mailSender.send(
        tokenService.constructPasswordResetTokenEmail(appUrl, token, token.getUser()));
  }

  @Override
  public void resetUserPassword(String token, String password) {
    userService.changePassword(tokenService.getUserByToken(token), password);
  }

}
