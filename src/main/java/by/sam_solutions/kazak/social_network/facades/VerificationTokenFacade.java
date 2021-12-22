package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.VerificationToken;

public interface VerificationTokenFacade {

  VerificationToken getByToken(String token);

  boolean isTokenExpired(String token);

}
