package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import by.sam_solutions.kazak.social_network.facades.VerificationTokenFacade;
import by.sam_solutions.kazak.social_network.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenFacadeImpl implements VerificationTokenFacade {

  @Autowired
  private VerificationTokenService verificationTokenService;

  @Override
  public VerificationToken getByToken(String token) {
    return verificationTokenService.getByToken(token);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return verificationTokenService.isTokenExpired(token);
  }

}
