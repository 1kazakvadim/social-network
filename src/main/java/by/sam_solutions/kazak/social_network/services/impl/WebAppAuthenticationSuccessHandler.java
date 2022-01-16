package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class WebAppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private ProfileService profileService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    Profile profile = profileService
        .getProfileByEmail(authentication.getName());
    request.getSession().setAttribute("profile", profile);
    response.sendRedirect(request.getContextPath() + "/id" + profile.getId());
  }

}
