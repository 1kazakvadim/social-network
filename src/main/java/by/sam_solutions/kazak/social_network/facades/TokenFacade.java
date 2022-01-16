package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Token;
import java.util.Locale;

public interface TokenFacade {

  Token getByToken(String token);

  void deleteByName(String token);

  boolean isTokenExpired(String token);

  void createAndSendPasswordResetToken(String email, String appUrl, Locale locale);

  void resetUserPassword(String token, String password);

}
