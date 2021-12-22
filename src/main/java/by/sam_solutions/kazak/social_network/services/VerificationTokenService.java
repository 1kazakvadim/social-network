package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;

public interface VerificationTokenService {

  void saveOrUpdate(VerificationToken verificationToken);

  VerificationToken getById(Long id);

  List<VerificationToken> getAll();

  void deleteById(Long id);

  VerificationToken createVerificationToken(User user);

  VerificationToken getByToken(String token);

  User getUserByToken(String token);

  boolean isTokenExpired(String token);

  SimpleMailMessage constructVerificationTokenEmail(String appUrl, VerificationToken token,
      User user);

}
