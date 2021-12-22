package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.VerificationToken;

public interface VerificationTokenDao extends IAbstractBaseDao<VerificationToken>{

  VerificationToken findByToken(String token);

}