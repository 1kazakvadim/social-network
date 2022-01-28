package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.FriendFacade;
import by.sam_solutions.kazak.social_network.facades.PhotoFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private FriendFacade friendFacade;

  @Autowired
  private PhotoFacade photoFacade;

  @GetMapping("/")
  public ModelAndView defaultPageRedirect(ModelAndView modelAndView, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      modelAndView.setViewName("login");
      return modelAndView;
    }
    response.sendRedirect(request.getContextPath() + "/id" + user.getId());
    return null;
  }

  @GetMapping("/id{userId}")
  public ModelAndView getProfilePage(ModelAndView modelAndView, @PathVariable Long userId,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile acceptFriendRequestProfile = profileFacade.getProfileByUserId(userId);
    Profile makeFriendRequestProfile = profileFacade.getProfileByUserId(user.getId());

    if (null == acceptFriendRequestProfile) {
      modelAndView.setViewName("redirect:/");
      return modelAndView;
    }

    Friend friend = friendFacade.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        acceptFriendRequestProfile.getId(),
        makeFriendRequestProfile.getId());
    if (Objects.equals(acceptFriendRequestProfile.getId(),
        makeFriendRequestProfile.getId())) {
      modelAndView.addObject("noButton", true);
    } else if (null == friend || friendFacade.isNonFriend(friend)) {
      modelAndView.addObject("addButton", true);
    } else if (friendFacade.hasFriendRequest(friend)) {
      modelAndView.addObject("requestButton", true);
    } else if (friendFacade.isFriend(friend)) {
      modelAndView.addObject("inFriendButton", true);
    }

    List<Profile> friends = profileFacade.getProfilesByFriendStatus(acceptFriendRequestProfile.getId(),
        FriendStatus.IN_FRIEND);
    Collections.shuffle(friends);
    modelAndView.addObject("friends", friends);
    modelAndView.addObject("friendCount", friends.size());

    List<Photo> photos = photoFacade.getAllByProfileId(acceptFriendRequestProfile.getId());
    Collections.shuffle(photos);
    modelAndView.addObject("photos", photos);
    modelAndView.addObject("photoCount", photos.size());

    modelAndView.addObject("profile", acceptFriendRequestProfile);
    modelAndView.setViewName("profile");
    return modelAndView;
  }

}
