package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoFacade {

  Photo getById(Long id);

  List<Photo> getAll();

  List<Photo> getAllByProfileId(Long id);

  void uploadProfilePhoto(MultipartFile file, UserPrincipal user);

  void deleteProfilePhoto(UserPrincipal user);

  void uploadInPhotos(MultipartFile file, Long profileId);

  boolean isMultipartFileValid(MultipartFile file);

}
