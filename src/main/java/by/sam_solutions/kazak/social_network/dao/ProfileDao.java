package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface ProfileDao extends IAbstractBaseDao<Profile> {

  Profile getProfileByEmail(String email);

  Profile getProfileByUserId(Long id);

  List<Profile> searchForProfiles(String search) throws Exception;

}
