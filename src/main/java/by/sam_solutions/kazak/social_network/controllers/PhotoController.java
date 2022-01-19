package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.PhotoFacade;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
  private PhotoFacade photoFacade;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/id{profileId}/photos")
  public ModelAndView getPhotosPage(HttpServletRequest request, ModelAndView modelAndView,
      @PathVariable Long profileId, Locale locale) {
    List<Photo> photos = photoFacade.getAllByProfileId(profileId);
    if (photos.size() == 0) {
      modelAndView.addObject("message",
          messageSource.getMessage("photoPage.message", null,
              locale));
      modelAndView.addObject("profileId", profileId);
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
      @PathVariable Long photoId) {
    Photo photo = photoFacade.getById(photoId);
    modelAndView.addObject("photo", photo);
    modelAndView.setViewName("photo-view");
    return modelAndView;
  }

}
