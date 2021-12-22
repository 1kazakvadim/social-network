package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.PasswordResetToken;
import by.sam_solutions.kazak.social_network.entities.User;

public interface PasswordResetTokenDao extends IAbstractBaseDao<PasswordResetToken> {

  PasswordResetToken getByToken(String token);

  User getUserByToken(String token);

}
