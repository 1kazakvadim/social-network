package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileFacadeImpl implements ProfileFacade {

  @Autowired
  private ProfileService profileService;

  @Override
  public List<Profile> getAll() {
    return profileService.getAll();
  }

  @Override
  public Profile getById(Long id) {
    return profileService.getById(id);
  }

  @Override
  public Profile getProfileByEmail(String email) {
    return profileService.getProfileByEmail(email);
  }

}
