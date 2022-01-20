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

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private PhotoFacade photoFacade;

  @Autowired
  private CommentFacade commentFacade;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/id{profileId}/photos")
  public ModelAndView getPhotosPage(HttpServletRequest request, ModelAndView modelAndView,
      @PathVariable Long profileId, Locale locale) {
    if (null == profileFacade.getById(profileId)) {
      modelAndView.setViewName("redirect:/");
      return modelAndView;
    }
    List<Photo> photos = photoFacade.getAllByProfileId(profileId);
    if (photos.size() == 0) {
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

  @PostMapping("/id{profileId}/photos/upload-photo")
  public ModelAndView uploadPhoto(ModelAndView modelAndView, @PathVariable Long profileId,
      @RequestParam("file") MultipartFile file, @AuthenticationPrincipal UserPrincipal user,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (!Objects.equals(user.getId(), profileFacade.getById(profileId).getUser().getId())) {
      modelAndView.setViewName("redirect:/id" + profileId + "/photos");
      return modelAndView;
    }
    if (null == file) {
      redirectAttributes.addFlashAttribute("messageError",
          messageSource.getMessage("uploadPage.error.empty", null,
              locale));
      modelAndView.setViewName("redirect:/id" + profileId + "/photos");
      return modelAndView;
    }
    if (!photoFacade.isMultipartFileValid(file)) {
      redirectAttributes.addFlashAttribute("messageError",
          messageSource.getMessage("uploadPage.error.isMultipartFileValid", null,
              locale));
      modelAndView.setViewName("redirect:/id" + profileId + "/photos");
      return modelAndView;
    }
    photoFacade.uploadInPhotos(file, profileId);
    modelAndView.setViewName("redirect:/id" + profileId + "/photos");
    return modelAndView;
  }

  @GetMapping("/id{profileId}/photos/{photoId}")
  public ModelAndView getViewPhotoPage(ModelAndView modelAndView, @PathVariable Long profileId,
      @PathVariable Long photoId, Locale locale) {
    Profile profile = profileFacade.getById(profileId);
    if (null == profile) {
      modelAndView.setViewName("redirect:/");
      return modelAndView;
    }
    Photo photo = photoFacade.getById(photoId);
    if (null == photo) {
      modelAndView.setViewName("redirect:/id" + profileId + "/photos");
      return modelAndView;
    }
    if (!photoFacade.isPhotoBelongsProfile(photo, profile)) {
      modelAndView.setViewName("redirect:/id" + profileId + "/photos");
      return modelAndView;
    }
    List<Comment> comments = commentFacade.getAllByPhotoId(photoId);
    if (comments.size() == 0) {
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

  @PostMapping("/id{profileId}/photos/{photoId}/add-description")
  public ModelAndView addPhotoDescription(ModelAndView modelAndView, @PathVariable Long profileId,
      @PathVariable Long photoId, @RequestParam String description,
      @AuthenticationPrincipal UserPrincipal user) {
    if (!Objects.equals(user.getId(), profileFacade.getById(profileId).getUser().getId())) {
      modelAndView.setViewName("redirect:/id" + profileId + "/photos/" + photoId);
      return modelAndView;
    }
    photoFacade.updateDescriptionInPhoto(description, photoId);
    modelAndView.setViewName("redirect:/id" + profileId + "/photos/" + photoId);
    return modelAndView;
  }

  @GetMapping("/id{profileId}/photos/{photoId}/delete")
  public ModelAndView deletePhoto(ModelAndView modelAndView, @PathVariable Long profileId,
      @PathVariable Long photoId, @AuthenticationPrincipal UserPrincipal user) {
    if (!Objects.equals(user.getId(), profileFacade.getById(profileId).getUser().getId())) {
      modelAndView.setViewName("redirect:/id" + profileId + "/photos/" + photoId);
      return modelAndView;
    }
    if (null == photoFacade.getById(photoId)) {
      modelAndView.setViewName("redirect:/id" + profileId + "/photos/");
      return modelAndView;
    }
    photoFacade.deleteById(photoId);
    modelAndView.setViewName("redirect:/id" + profileId + "/photos/");
    return modelAndView;
  }

}
