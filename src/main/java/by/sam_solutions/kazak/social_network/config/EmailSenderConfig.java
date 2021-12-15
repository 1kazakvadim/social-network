package by.sam_solutions.kazak.social_network.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class EmailSenderConfig {

  @Autowired
  private Environment environment;

  @Bean
  public JavaMailSender mailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(environment.getRequiredProperty("mail.host"));
    mailSender.setPort(environment.getRequiredProperty("mail.port", Integer.class));
    mailSender.setUsername(environment.getRequiredProperty("mail.username"));
    mailSender.setPassword(environment.getRequiredProperty("mail.password"));
    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", environment.getRequiredProperty("mail.protocol"));
    properties.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
    properties
        .put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls"));
    properties.put("mail.debug", environment.getRequiredProperty("mail.debug"));
    return mailSender;
  }

}
