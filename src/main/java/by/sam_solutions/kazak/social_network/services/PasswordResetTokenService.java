package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.PasswordResetToken;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;

public interface PasswordResetTokenService {

  void saveOrUpdate(PasswordResetToken passwordResetToken);

  PasswordResetToken getById(Long id);

  List<PasswordResetToken> getAll();

  void deleteById(Long id);

  PasswordResetToken getByToken(String token);

  User getUserByToken(String token);

  PasswordResetToken createPasswordResetTokenForUser(String email);

  SimpleMailMessage constructPasswordResetTokenEmail(String appUrl, PasswordResetToken token,
      User user);

  boolean isTokenExpired(String token);

}
