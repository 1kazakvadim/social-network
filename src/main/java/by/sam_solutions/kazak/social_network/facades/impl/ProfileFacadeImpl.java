package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.ContactInformationConverter;
import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import by.sam_solutions.kazak.social_network.services.FriendService;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
  private FriendService friendService;

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
  public List<Profile> getProfilesByFriendStatus(Long id, FriendStatus friendStatus) {
    return profileService.getUniqueFriendsProfiles(
        friendService.getAllByProfileIdAndFriendStatus(id, friendStatus), id);
  }

  @Override
  public List<Profile> searchForProfiles(String search) {
    try {
      return profileService.searchForProfiles(search);
    } catch (Exception exp) {
      logger.debug("Error searching for profiles");
      exp.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public void updateContactInformationInProfile(ContactInformationDTO contactInformationDTO) {
    Profile profile = profileService.getById(contactInformationDTO.getId());
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
    updatedProfile.setProfilePhotoName(profile.getProfilePhotoName());
    updatedProfile.setTimeRegistration(profile.getTimeRegistration());
    updatedProfile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(updatedProfile);
  }

  @Override
  public void changeProfileLock(Profile profile, boolean isLocked) {
    profile.getUser().setLocked(isLocked);
    profile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(profile);
  }

}
