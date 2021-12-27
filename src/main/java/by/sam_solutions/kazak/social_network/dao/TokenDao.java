package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.Token;

public interface TokenDao extends IAbstractBaseDao<Token> {

  Token getByToken(String token);

  User getUserByToken(String token);

}
