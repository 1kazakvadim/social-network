package by.sam_solutions.kazak.social_network.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.sam_solutions.kazak.social_network")
public class WebMvcConfig {

}
