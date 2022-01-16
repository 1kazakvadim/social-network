package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.StorageFacade;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import by.sam_solutions.kazak.social_network.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageFacadeImpl implements StorageFacade {

  private static String DEFAULT_PROFILE_PHOTO_NAME = "default-profile-photo";

  @Autowired
  private StorageService storageService;

  @Autowired
  private ProfileService profileService;

  @Override
  public void uploadProfilePhoto(MultipartFile file, UserPrincipal user) {
    Profile profile = profileService.getProfileByUserId(user.getId());
    String photoName = storageService.upload(file);
    profile.setProfilePhotoName(photoName);
    profileService.saveOrUpdate(profile);
  }

  @Override
  public void deleteProfilePhoto(UserPrincipal user) {
    Profile profile = profileService.getProfileByUserId(user.getId());
    if(profile.getProfilePhotoName().equals(DEFAULT_PROFILE_PHOTO_NAME)) {
      return;
    }
    storageService.delete(profile.getProfilePhotoName());
    profile.setProfilePhotoName(DEFAULT_PROFILE_PHOTO_NAME);
    profileService.saveOrUpdate(profile);
  }

  @Override
  public boolean isMultipartFileValid(MultipartFile file) {
    return storageService.isMultipartFileValid(file);
  }

}
