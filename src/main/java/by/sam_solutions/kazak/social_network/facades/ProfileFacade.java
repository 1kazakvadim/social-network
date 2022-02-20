package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface ProfileFacade {

  List<Profile> getAll();

  List<Profile> getAll(Integer page, Integer size);

  Profile getById(Long id);

  Profile getProfileByUserId(Long id);

  List<Profile> getProfilesByFriendStatus(Long id, FriendStatus friendStatus);

  List<Profile> getProfilesByFriendStatus(Long id, FriendStatus friendStatus, Integer page,
      Integer size);

  List<Profile> searchForProfiles(String search);

  void updateContactInformationInProfile(ContactInformationDTO contactInformationDTO);

  void changeProfileLock(Profile profile, boolean isLocked);

  Long countProfiles();

}
