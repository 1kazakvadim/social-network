package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.FriendFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendController {

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private FriendFacade friendFacade;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/id{userId}/friends")
  public ModelAndView modelAndView(ModelAndView modelAndView, @PathVariable Long userId,
      @RequestParam(value = "friendPage", required = false, defaultValue = "0") Integer friendPage,
      @RequestParam(value = "friendSize", required = false, defaultValue = "10") Integer friendSize,
      @RequestParam(value = "friendRequestPage", required = false, defaultValue = "0") Integer friendRequestPage,
      @RequestParam(value = "friendRequestSize", required = false, defaultValue = "10") Integer friendRequestSize,
      Locale locale) {
    Profile profile = profileFacade.getProfileByUserId(userId);
    if (profile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    if (friendPage < 0 || friendSize < 0 || friendRequestPage < 0 || friendRequestSize < 0) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_FRIENDS);
      return modelAndView;
    }
    List<Profile> friends = profileFacade.getProfilesByFriendStatus(profile.getId(),
        FriendStatus.IN_FRIEND, friendPage, friendSize);
    List<Profile> friendRequests = profileFacade.getProfilesByFriendStatus(profile.getId(),
        FriendStatus.FRIEND_REQUEST, friendRequestPage, friendRequestSize);
    if (friends.isEmpty()) {
      modelAndView.addObject("noFriendMessage",
          messageSource.getMessage("friendPage.noFriendMessage", null,
              locale));
    }
    if (friendRequests.isEmpty()) {
      modelAndView.addObject("noFriendRequestMessage",
          messageSource.getMessage("friendPage.noFriendRequestMessage", null,
              locale));
    }
    modelAndView.addObject("friendPage", friendPage);
    modelAndView.addObject("friendRequestPage", friendRequestPage);
    modelAndView.addObject("friendSize", friendSize);
    modelAndView.addObject("friendRequestSize", friendRequestSize);
    modelAndView.addObject("totalFriend",
        Math.ceil((double) friendFacade.countByProfileIdAndFriendStatus(
            profile.getId(), FriendStatus.IN_FRIEND) / friendSize));
    modelAndView.addObject("totalFriendRequests",
        Math.ceil((double) friendFacade.countByProfileIdAndFriendStatus(
            profile.getId(), FriendStatus.FRIEND_REQUEST) / friendRequestSize));
    modelAndView.addObject("elementsOnPage", WebConstants.ELEMENTS_PAGE_COUNT_10);
    modelAndView.addObject("friends", friends);
    modelAndView.addObject("friendRequests", friendRequests);
    modelAndView.setViewName("friends");
    return modelAndView;
  }

  @GetMapping("/id{userId}/add-to-friends")
  public ModelAndView addToFriends(ModelAndView modelAndView, @PathVariable Long userId,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile acceptFriendRequestProfile = profileFacade.getProfileByUserId(userId);
    Profile makeFriendRequestProfile = profileFacade.getProfileByUserId(user.getId());
    if (acceptFriendRequestProfile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    if (Objects.equals(makeFriendRequestProfile.getId(), acceptFriendRequestProfile.getId())) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
      return modelAndView;
    }
    Friend friend = friendFacade.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfile.getId(),
        acceptFriendRequestProfile.getId());
    if (friend == null) {
      friendFacade.createFriendWithFriendStatus(makeFriendRequestProfile,
          acceptFriendRequestProfile, FriendStatus.FRIEND_REQUEST);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isNonFriend(friend)) {
      friendFacade.changeFriendStatus(friend, FriendStatus.FRIEND_REQUEST);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.hasFriendRequest(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isFriend(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    return modelAndView;
  }

  @GetMapping("/id{userId}/unfriend")
  public ModelAndView unfriend(ModelAndView modelAndView, @PathVariable Long userId,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile acceptFriendRequestProfile = profileFacade.getProfileByUserId(userId);
    Profile makeFriendRequestProfile = profileFacade.getProfileByUserId(user.getId());
    if (acceptFriendRequestProfile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    if (Objects.equals(makeFriendRequestProfile.getId(), acceptFriendRequestProfile.getId())) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
      return modelAndView;
    }
    Friend friend = friendFacade.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfile.getId(),
        acceptFriendRequestProfile.getId());
    if (friend == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isNonFriend(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.hasFriendRequest(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isFriend(friend)) {
      friendFacade.changeFriendStatus(friend, FriendStatus.NON_FRIEND);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    return modelAndView;
  }

  @GetMapping("/id{userId}/cancel-request")
  public ModelAndView cancelRequest(ModelAndView modelAndView, @PathVariable Long userId,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile acceptFriendRequestProfile = profileFacade.getProfileByUserId(userId);
    Profile makeFriendRequestProfile = profileFacade.getProfileByUserId(user.getId());
    if (acceptFriendRequestProfile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    if (Objects.equals(makeFriendRequestProfile.getId(), acceptFriendRequestProfile.getId())) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
      return modelAndView;
    }
    Friend friend = friendFacade.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfile.getId(),
        acceptFriendRequestProfile.getId());
    if (friend == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isNonFriend(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isFriend(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.hasFriendRequest(friend)) {
      friendFacade.changeFriendStatus(friend, FriendStatus.NON_FRIEND);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    return modelAndView;
  }

  @GetMapping("/id{userId}/accept-request")
  public ModelAndView acceptRequest(ModelAndView modelAndView, @PathVariable Long userId,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile acceptFriendRequestProfile = profileFacade.getProfileByUserId(userId);
    Profile makeFriendRequestProfile = profileFacade.getProfileByUserId(user.getId());
    if (acceptFriendRequestProfile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    if (Objects.equals(makeFriendRequestProfile.getId(), acceptFriendRequestProfile.getId())) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
      return modelAndView;
    }
    Friend friend = friendFacade.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfile.getId(),
        acceptFriendRequestProfile.getId());
    if (friend == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isNonFriend(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.isFriend(friend)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    if (friendFacade.hasFriendRequest(friend)) {
      friendFacade.changeFriendStatus(friend, FriendStatus.IN_FRIEND);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId);
      return modelAndView;
    }
    return modelAndView;
  }

}
