package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.Comment;
import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.CommentFacade;
import by.sam_solutions.kazak.social_network.facades.PhotoFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class PhotoController {

  private static final String ROLE_ADMIN = "ADMIN";

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private PhotoFacade photoFacade;

  @Autowired
  private CommentFacade commentFacade;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/id{userId}/photos")
  public ModelAndView getPhotosPage(HttpServletRequest request, ModelAndView modelAndView,
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @PathVariable Long userId, Locale locale) {
    Profile profile = profileFacade.getProfileByUserId(userId);
    if (profile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    List<Photo> photos = photoFacade.getAllByProfileId(profile.getId(), page);
    if (photos.isEmpty()) {
      modelAndView.addObject("message",
          messageSource.getMessage("photoPage.message", null,
              locale));
      modelAndView.setViewName("photos");
      return modelAndView;
    }
    modelAndView.addObject("photos", photos);
    modelAndView.setViewName("photos");
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
    if (inputFlashMap != null) {
      modelAndView.addObject("messageError", inputFlashMap.get("messageError"));
    }
    return modelAndView;
  }

  @PostMapping("/id{userId}/photos/upload-photo")
  public ModelAndView uploadPhoto(ModelAndView modelAndView, @PathVariable Long userId,
      @RequestParam("file") MultipartFile file, @AuthenticationPrincipal UserPrincipal user,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (!Objects.equals(user.getId(), userId)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos");
      return modelAndView;
    }
    if (file == null) {
      redirectAttributes.addFlashAttribute("messageError",
          messageSource.getMessage("uploadPage.error.empty", null,
              locale));
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos");
      return modelAndView;
    }
    if (!photoFacade.isMultipartFileValid(file)) {
      redirectAttributes.addFlashAttribute("messageError",
          messageSource.getMessage("uploadPage.error.isMultipartFileValid", null,
              locale));
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos");
      return modelAndView;
    }
    photoFacade.uploadInPhotos(file, profileFacade.getProfileByUserId(userId).getId());
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos");
    return modelAndView;
  }

  @GetMapping("/id{userId}/photos/{photoId}")
  public ModelAndView getViewPhotoPage(ModelAndView modelAndView, @PathVariable Long userId,
      @PathVariable Long photoId, Locale locale) {
    Profile profile = profileFacade.getProfileByUserId(userId);
    if (profile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_HOMEPAGE_URN);
      return modelAndView;
    }
    Photo photo = photoFacade.getById(photoId);
    if (photo == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos");
      return modelAndView;
    }
    if (!photoFacade.isPhotoBelongsProfile(photo, profile)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos");
      return modelAndView;
    }
    List<Comment> comments = commentFacade.getAllByPhotoId(photoId);
    if (comments.isEmpty()) {
      modelAndView.addObject("message",
          messageSource.getMessage("photoView.message", null,
              locale));
    }
    modelAndView.addObject("photo", photo);
    modelAndView.addObject("profile", profile);
    modelAndView.addObject("comments", comments);
    modelAndView.setViewName("photo-view");
    return modelAndView;
  }

  @PostMapping("/id{userId}/photos/{photoId}/add-comment")
  public ModelAndView addComment(ModelAndView modelAndView, @PathVariable Long userId,
      @PathVariable Long photoId, @RequestParam String text,
      @AuthenticationPrincipal UserPrincipal user) {
    commentFacade.addComment(text, photoId, user.getId());
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
    return modelAndView;
  }

  @PostMapping("/id{userId}/photos/{photoId}/delete-comment")
  public ModelAndView deleteComment(ModelAndView modelAndView, @PathVariable Long userId,
      @PathVariable Long photoId, @RequestParam Long commentId,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile profile = profileFacade.getProfileByUserId(user.getId());
    Comment comment = commentFacade.getById(commentId);
    if (profile == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
      return modelAndView;
    }
    if (comment == null) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
      return modelAndView;
    }
    if (profile.getUser().getRole().getName().equals(ROLE_ADMIN) || profile.getUser().getId()
        .equals(comment.getProfile().getUser().getId())) {
      commentFacade.deleteComment(commentId, profile);
    }
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
    return modelAndView;
  }

  @PostMapping("/id{userId}/photos/{photoId}/add-description")
  public ModelAndView addPhotoDescription(ModelAndView modelAndView, @PathVariable Long userId,
      @PathVariable Long photoId, @RequestParam String description,
      @AuthenticationPrincipal UserPrincipal user) {
    if (!Objects.equals(user.getId(), userId)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
      return modelAndView;
    }
    photoFacade.updateDescriptionInPhoto(description, photoId);
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
    return modelAndView;
  }

  @GetMapping("/id{userId}/photos/{photoId}/delete")
  public ModelAndView deletePhoto(ModelAndView modelAndView, @PathVariable Long userId,
      @PathVariable Long photoId, @AuthenticationPrincipal UserPrincipal user) {
    if (!Objects.equals(user.getId(), userId)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/" + photoId);
      return modelAndView;
    }
    if (null == photoFacade.getById(photoId)) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/");
      return modelAndView;
    }
    photoFacade.deleteById(photoId);
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + userId + "/photos/");
    return modelAndView;
  }

}
