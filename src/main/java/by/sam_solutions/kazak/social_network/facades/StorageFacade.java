package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

public interface StorageFacade {

  void uploadProfilePhoto(MultipartFile file, UserPrincipal user);

  void deleteProfilePhoto(UserPrincipal user);

  boolean isMultipartFileValid(MultipartFile file);

}
