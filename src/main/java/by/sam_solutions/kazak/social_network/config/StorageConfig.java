package by.sam_solutions.kazak.social_network.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:storage.properties")
public class StorageConfig {

  @Autowired
  private Environment environment;

  @Bean
  public AmazonS3 awsS3Client() {
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
        environment.getRequiredProperty("aws.keyId"),
        environment.getRequiredProperty("aws.accessKey"));
    return AmazonS3ClientBuilder.standard()
        .withRegion(Regions.fromName(environment.getRequiredProperty("aws.region")))
        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
  }

}
