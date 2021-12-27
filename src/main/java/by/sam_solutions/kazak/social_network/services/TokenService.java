package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.Token;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;

public interface TokenService {

  void saveOrUpdate(Token token);

  Token getById(Long id);

  List<Token> getAll();

  void deleteById(Long id);

  Token createVerificationToken(User user);

  Token createPasswordResetToken(String email);

  Token getByToken(String token);

  User getUserByToken(String token);

  boolean isTokenExpired(String token);

  SimpleMailMessage constructVerificationTokenEmail(String appUrl, Token token,
      User user);

  SimpleMailMessage constructPasswordResetTokenEmail(String appUrl, Token token,
      User user);

}
