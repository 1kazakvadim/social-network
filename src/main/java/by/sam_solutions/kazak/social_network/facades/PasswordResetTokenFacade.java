package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.PasswordResetToken;

public interface PasswordResetTokenFacade {

  PasswordResetToken getByToken(String token);

  void createAndSendPasswordResetToken(String email, String appUrl);

  void resetUserPassword(String token, String password);

  boolean isTokenExpired(String token);

}
