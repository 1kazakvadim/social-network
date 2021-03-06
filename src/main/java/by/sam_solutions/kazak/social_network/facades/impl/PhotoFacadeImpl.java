package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.PhotoFacade;
import by.sam_solutions.kazak.social_network.services.PhotoService;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import by.sam_solutions.kazak.social_network.services.StorageService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PhotoFacadeImpl implements PhotoFacade {

  private static final String DEFAULT_PROFILE_PHOTO_NAME = "default-profile-photo";

  @Autowired
  private StorageService storageService;

  @Autowired
  private PhotoService photoService;

  @Autowired
  private ProfileService profileService;

  @Override
  public void updateDescriptionInPhoto(String description, Long photoId) {
    Photo photo = photoService.getById(photoId);
    photo.setDescription(description);
    photoService.saveOrUpdate(photo);
  }

  @Override
  public Photo getById(Long id) {
    return photoService.getById(id);
  }

  @Override
  public List<Photo> getAll() {
    return photoService.getAll();
  }

  @Override
  public List<Photo> getAllByProfileId(Long id, Integer page, Integer size) {
    return photoService.getAllByProfileId(id, page, size);
  }

  @Override
  public List<Photo> getAllByProfileId(Long id) {
    return photoService.getAllByProfileId(id);
  }

  @Override
  public void deleteById(Long id) {
    Photo photo = photoService.getById(id);
    storageService.delete(photo.getName());
    photoService.deleteById(photo.getId());
  }

  @Override
  public void uploadProfilePhoto(MultipartFile file, Profile profile) {
    String photoName = storageService.upload(file);
    profile.setProfilePhotoName(photoName);
    profileService.saveOrUpdate(profile);
  }

  @Override
  public void deleteProfilePhoto(Profile profile) {
    if (profile.getProfilePhotoName().equals(DEFAULT_PROFILE_PHOTO_NAME)) {
      return;
    }
    storageService.delete(profile.getProfilePhotoName());
    profile.setProfilePhotoName(DEFAULT_PROFILE_PHOTO_NAME);
    profileService.saveOrUpdate(profile);
  }

  @Override
  public void uploadInPhotos(MultipartFile file, Long profileId) {
    Photo photo = new Photo();
    String photoName = storageService.upload(file);
    photo.setProfile(profileService.getById(profileId));
    photo.setName(photoName);
    photo.setTimeCreation(LocalDateTime.now());
    photoService.saveOrUpdate(photo);
  }

  @Override
  public boolean isMultipartFileValid(MultipartFile file) {
    return storageService.isMultipartFileValid(file);
  }

  @Override
  public boolean isPhotoBelongsProfile(Photo photo, Profile profile) {
    return Objects.equals(photo.getProfile().getId(), profile.getId());
  }

  @Override
  public Long countByProfileId(Long id) {
    return photoService.countByProfileId(id);
  }

}
