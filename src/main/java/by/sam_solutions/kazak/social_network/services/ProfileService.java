package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface ProfileService {

  void saveOrUpdate(Profile profile);

  Profile getById(Long id);

  List<Profile> getAll();

  void deleteById(Long id);

  Profile getProfileByEmail(String email);

}
