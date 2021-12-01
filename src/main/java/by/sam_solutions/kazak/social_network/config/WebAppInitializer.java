package by.sam_solutions.kazak.social_network.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(final ServletContext sc) throws ServletException {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.setConfigLocation("by.sam_solutions.kazak.social_network");
    sc.addListener(new ContextLoaderListener(context));
    ServletRegistration.Dynamic dispatcher =
        sc.addServlet("dispatcher", new DispatcherServlet(context));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }

}
