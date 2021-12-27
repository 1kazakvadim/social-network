package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Token;

public interface TokenFacade {

  Token getByToken(String token);

  boolean isTokenExpired(String token);

  void createAndSendPasswordResetToken(String email, String appUrl);

  void resetUserPassword(String token, String password);

}
