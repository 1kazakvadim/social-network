package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoFacade {

  void updateDescriptionInPhoto(String description, Long photoId);

  Photo getById(Long id);

  List<Photo> getAll();

  List<Photo> getAllByProfileId(Long id, Integer page, Integer size);

  List<Photo> getAllByProfileId(Long id);

  void deleteById(Long id);

  void uploadProfilePhoto(MultipartFile file, Profile profile);

  void deleteProfilePhoto(Profile profile);

  void uploadInPhotos(MultipartFile file, Long profileId);

  boolean isMultipartFileValid(MultipartFile file);

  boolean isPhotoBelongsProfile(Photo photo, Profile profile);

  Long countByProfileId(Long id);

}
