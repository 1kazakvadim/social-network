package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import java.util.List;

public interface VerificationTokenService {

  void saveOrUpdate(VerificationToken verificationToken);

  VerificationToken getById(Long id);

  List<VerificationToken> getAll();

  void deleteById(Long id);

  VerificationToken findByToken(String token);

}
