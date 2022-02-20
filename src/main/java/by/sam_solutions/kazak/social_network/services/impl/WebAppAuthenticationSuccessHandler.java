package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class WebAppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private static final Logger logger = LoggerFactory.getLogger(WebAppAuthenticationSuccessHandler.class);

  @Autowired
  private UserService userService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    logger.info("success authentication by {}", authentication.getName());
    User user = userService.getByEmail(authentication.getName());
    request.getSession().setAttribute("user", user);
    response.sendRedirect(request.getContextPath() + "/id" + user.getId());
  }

}
