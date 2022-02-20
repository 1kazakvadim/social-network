package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface ProfileService {

  void saveOrUpdate(Profile profile);

  Profile getById(Long id);

  List<Profile> getAll();

  List<Profile> getAll(Integer page, Integer size);

  void deleteById(Long id);

  Profile getProfileByUserId(Long id);

  List<Profile> getUniqueFriendsProfiles(List<Friend> friends, Long id);

  List<Profile> searchForProfiles(String search) throws Exception;

  boolean isFieldContainsSpecialCharacters(String string);

  Long countProfiles();

}
