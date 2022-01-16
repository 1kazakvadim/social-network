package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.ContactInformationConverter;
import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileFacadeImpl implements ProfileFacade {

  private final Logger logger = LoggerFactory.getLogger(ProfileFacadeImpl.class);

  @Autowired
  private ProfileService profileService;

  @Autowired
  private ContactInformationConverter contactInformationConverter;

  @Override
  public List<Profile> getAll() {
    return profileService.getAll();
  }

  @Override
  public Profile getById(Long id) {
    return profileService.getById(id);
  }

  @Override
  public Profile getProfileByUserId(Long id) {
    return profileService.getProfileByUserId(id);
  }

  @Override
  public void updateContactInformationInProfile(ContactInformationDTO contactInformationDTO) {
    Profile profile = profileService.getProfileByUserId(contactInformationDTO.getId());
    Profile updatedProfile = new Profile();
    try {
      updatedProfile = (Profile) contactInformationConverter.convertTargetToSourceClass(
          contactInformationDTO, Profile.class);
    } catch (Exception exp) {
      logger.debug("Error converting {} to {}", ContactInformationDTO.class.getName(),
          Profile.class.getName());
      exp.printStackTrace();
    }
    updatedProfile.setUser(profile.getUser());
    updatedProfile.setBasicInformation(profile.getBasicInformation());
    updatedProfile.setFriendCount(profile.getFriendCount());
    updatedProfile.setProfilePhotoName(profile.getProfilePhotoName());
    updatedProfile.setTimeRegistration(profile.getTimeRegistration());
    updatedProfile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(updatedProfile);
  }

}
