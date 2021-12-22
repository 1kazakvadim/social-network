package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Profile;

public interface ProfileDao extends IAbstractBaseDao<Profile> {

  Profile getProfileByEmail(String email);

}
