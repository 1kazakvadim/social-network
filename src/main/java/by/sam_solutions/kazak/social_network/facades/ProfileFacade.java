package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface ProfileFacade {

  List<Profile> getAll();

  Profile getById(Long id);

  Profile getProfileByEmail(String email);

}