package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;

public interface VerificationTokenDao extends IAbstractBaseDao<VerificationToken> {

  VerificationToken getByToken(String token);

  User getUserByToken(String token);

}
